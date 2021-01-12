package com.foundmypet.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.foundmypet.Model.User
import com.foundmypet.R
import com.foundmypet.UserProfileActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firebaseUser:FirebaseUser
    private var checker = ""
    private var myUri = ""
    private var imageUri: Uri?=null
    private var storageProfilePicRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        storageProfilePicRef = FirebaseStorage.getInstance().reference.child("Profile Pictures")

        userInfo()

        button_cancel_edit_profile.setOnClickListener{
            this.finish()
        }

        change_proffile_image_button.setOnClickListener{
            checker = "clicked"
            CropImage.activity()
                .setAspectRatio(1,1)
                .start(this@EditProfileActivity)
        }

        button_confirm_profile_edit.setOnClickListener{
            if(checker == "clicked")
            {
                uploadImageAndUpdateInfo()
            }
            else
            {
                updateUserInfoOnly()
            }
        }
    }

    private fun userInfo(){
        val userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.uid)
        userRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val user = snapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).placeholder(R.drawable.add_image_profile).into(image_user_profile)
                    edit_city_field.setText(user!!.getCity())
                    edit_country_field.setText(user!!.getCountry())
                    edit_username_field.setText(user!!.getUser())
                    edit_cellphone_number_field.setText(user!!.getCellphone())
                    edit_email_field.setText(user!!.getEmail())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    private fun updateUserInfoOnly() {

        if (validateUserName() && validateCity() && validateCountry() && validateCellphoneNmber() && validateEmail()){
            val userRef = FirebaseDatabase.getInstance().getReference().child("Users")
            val userMap = HashMap<String, Any>()
            userMap["user"] = edit_username_field.text.toString()
            userMap["city"] = edit_city_field.text.toString()
            userMap["country"] = edit_country_field.text.toString()
            userMap["cellphone"] = edit_cellphone_number_field.text.toString()
            userMap["email"] = edit_email_field.text.toString()
            userRef.child(firebaseUser.uid).updateChildren(userMap)
            Toast.makeText(
                this,
                "Informacion de la cuenta actualizada",
                Toast.LENGTH_LONG
            ).show()
            val intent =
                Intent(this@EditProfileActivity, UserProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
                val result = CropImage.getActivityResult(data)
                imageUri = result.uri
                image_user_profile.setImageURI(imageUri)
            }
        }

        private fun uploadImageAndUpdateInfo() {

            when {

                imageUri == null -> Toast.makeText(
                    this,
                    "Selecciona una imagen primero",
                    Toast.LENGTH_LONG
                ).show()

                else -> {
                    val progressDialog = ProgressDialog(this)
                    progressDialog.setTitle("Aplicando cambios")
                    progressDialog.setMessage("Espere por favor")
                    progressDialog.show()

                    val fileRef = storageProfilePicRef!!.child(firebaseUser!!.uid + ".jpg")
                    var uploadTask: StorageTask<*>
                    uploadTask = fileRef.putFile(imageUri!!)
                    uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                                progressDialog.dismiss()
                            }
                        }
                        return@Continuation fileRef.downloadUrl

                    }).addOnCompleteListener(OnCompleteListener<Uri> { task ->
                        if (task.isSuccessful) {
                            val downloadUrl = task.result
                            myUri = downloadUrl.toString()
                            val ref = FirebaseDatabase.getInstance().reference.child("Users")
                            val userMap = hashMapOf<String, Any>()
                            userMap["image"] = myUri
                            userMap["user"] = edit_username_field.text.toString()
                            userMap["city"] = edit_city_field.text.toString()
                            userMap["country"] = edit_country_field.text.toString()
                            userMap["cellphone"] = edit_cellphone_number_field.text.toString()
                            userMap["email"] = edit_email_field.text.toString()
                            ref.child(firebaseUser.uid).updateChildren(userMap)

                            Toast.makeText(
                                this,
                                "Informacion de la cuenta actualizada",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent =
                                Intent(this@EditProfileActivity, UserProfileActivity::class.java)
                            startActivity(intent)
                            finish()
                            progressDialog.dismiss()
                        } else {
                            progressDialog.dismiss()
                        }
                    })
                }
            }

        }


        private fun validateEmail(): Boolean {
            val emailInput: String = edit_email_field.text.toString().trim()
            return if (emailInput.isEmpty()) {
                edit_email_field?.error = "Este Campo no puede estar Vacio"
                false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                edit_email_field?.error = "Ingrese un Correo Valido"
                false
            } else {
                edit_email_field?.error = null
                true
            }
        }


        private fun validateUserName(): Boolean {
            val userName: String = edit_username_field.text.toString().trim()
            return if (userName.isEmpty()) {
                edit_username_field?.error = "Este Campo no puede estar Vacio"
                false
            } else {
                edit_username_field?.error = null
                true
            }
        }

        private fun validateCity(): Boolean {
            val city: String = edit_city_field.text.toString().trim()
            return if (city.isEmpty()) {
                edit_city_field?.error = "Este Campo no puede estar Vacio"
                false
            } else {
                edit_city_field?.error = null
                true
            }
        }

        private fun validateCountry(): Boolean {
            val country: String = edit_country_field.text.toString().trim()
            return if (country.isEmpty()) {
                edit_country_field?.error = "Este Campo no puede estar Vacio"
                false
            } else {
                edit_country_field?.error = null
                true
            }
        }

        private fun validateCellphoneNmber(): Boolean {
            val number: String = edit_cellphone_number_field.text.toString().trim()
            return if (number.isEmpty()) {
                edit_cellphone_number_field?.error = "Este Campo no puede estar Vacio"
                false
            } else if (number.length > 8) {
                edit_cellphone_number_field?.error = "Numero de telefon invalido"
                false
            } else {
                edit_cellphone_number_field?.error = null
                true
            }
    }
}