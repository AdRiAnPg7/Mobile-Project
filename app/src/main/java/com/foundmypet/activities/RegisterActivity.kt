package com.foundmypet

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register.*
import java.io.ByteArrayOutputStream


class RegisterActivity : AppCompatActivity() {

    //Vars Auth
    private var mAuth : FirebaseAuth? = null

    // Vars User
    private var imgUserPhoto : ImageView? = null
    private var imageUri : Uri? = null

    // Vars Text Inputs
    private var textEmail: TextInputLayout? = null
    private var textUserName: TextInputLayout? = null
    private var textPass: TextInputLayout? = null
    private var textConfirmPass: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Init Vars Text Inputs
        textEmail = findViewById<TextInputLayout>(R.id.text_container_email)
        textUserName = findViewById<TextInputLayout>(R.id.text_container_user_name)
        textPass = findViewById<TextInputLayout>(R.id.text_container_new_password)
        textConfirmPass = findViewById<TextInputLayout>(R.id.text_container_new_confirm_password)
        imgUserPhoto =findViewById<ImageView>(R.id.image_new_user_photo)

        // Firebase
        mAuth = FirebaseAuth.getInstance()

        // Setup
        setup()
    }


    private fun setup(){
        button_login_confirm.setOnClickListener {
            register()
        }
        image_new_user_photo.setOnClickListener {
            setImage()
        }
    }


    private fun register(){

        if( validateEmail() && validatePassword() && validateUserName()){
            mAuth?.createUserWithEmailAndPassword(
                text_new_email.text.toString(),
                text_new_password.text.toString())
                ?.addOnCompleteListener(this){ task ->
                    if (task.isSuccessful){
                        saveUserInfo(text_new_email.text.toString(),text_new_user_name.text.toString())
                        //updateUserInfo(textUserName, imageUri, mAuth!!.currentUser)
                        //showHome(task.result?.user?.email?:"Este Email No existe", ProviderType.BASIC)
                    }else{
                        //mAuth!!.signOut()
                        showAlert()
                    }
                }
        }

    }

    private fun saveUserInfo(new_Email: String, new_UserName: String) {
        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String, Any>()
        userMap["uid"] = currentUserID
        userMap["email"] = new_Email
        userMap["user"] = new_UserName.toLowerCase()
        userMap["image"] = "https://firebasestorage.googleapis.com/v0/b/foundmypet-b1dae.appspot.com/o/Default%20Images%2Fprofile.png?alt=media&token=d65da2f2-1287-4d42-9b12-eeeb2646289a"

        userRef.child(currentUserID).setValue(userMap)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Cuenta Creada Correctamente!",Toast.LENGTH_LONG).show()

                    val intent =  Intent(this, HomePageActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

                    startActivity(intent)
                    finish()
                }
                else
                {
                    //FirebaseAuth.getInstance().signOut()
                    //showAlert()
                }
        }
    }


    private fun updateUserInfo(textUserName: TextInputLayout?, imageUri: Uri?, currentUser: FirebaseUser?) {
//        var mStorage:StorageReference? = FirebaseStorage.getInstance().reference.child("user_photos")
//        var imageFilePath:StorageReference? = mStorage?.child(imageUri?.lastPathSegment.toString())
//
//
//        if (imageUri != null) {
//            imageFilePath?.putFile(imageUri)?.addOnSuccessListener {
//                imageFilePath.downloadUrl.addOnSuccessListener {
//                    var  profileUpdate: UserProfileChangeRequest? = new UserProfileChangeRequest.Builder().setDisplayName(
//                        textUserName.toString()
//                    ).setUri(uri)
//                        .build()
//                }
//
//            }
//        }

    }


    private fun setImage(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                // Permission Denied
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, 1001)
            }
            else {
                // Permission Granted
                pickImageFromGallery()
            }
        }else {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            1001 -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }else{
                    Toast.makeText(this,"Permiso Denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 1000){
            imageUri = data?.data!!
            image_new_user_photo.setImageURI(imageUri)
            //val imageBitmap = data?.extras?.get("data") as Bitmap
            //uploadImageAndSave()
        }
    }

    private fun uploadImageAndSave() {
//        val baos = ByteArrayOutputStream()
//        val storageRef = FirebaseStorage.getInstance()
//            .reference.child("pics/${FirebaseAuth.getInstance().currentUser?.uid}")
//
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//        val image = baos.toByteArray()
//        val upload = storageRef.putBytes(image)
//
//        upload.addOnCompleteListener{ uploadTask ->
//            if(uploadTask.isSuccessful){
//                storageRef.downloadUrl.addOnCompleteListener { urlTask ->
//                    urlTask.result?.let{
//                        imageUri = it
//                        image_new_user_photo.setImageBitmap(bitmap)
//                    }
//                }
//            }
//
//        }
    }

    private fun validateEmail(): Boolean {
        val emailInput: String = textEmail?.getEditText()?.getText().toString().trim()
        return if (emailInput.isEmpty()) {
            textEmail?.error = "Este Campo no puede estar Vacio"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textEmail?.error = "Ingrese un Correo Valido"
            false
        } else {
            textEmail?.error = null
            true
        }
    }

    private fun validateUserName(): Boolean {
        val usernameInput: String = textUserName?.getEditText()?.getText().toString().trim()
        return if (usernameInput.isEmpty()) {
            textUserName?.error= "Este Campo no puede estar Vacio"
            false
        } else if (usernameInput.length > 15) {
            textUserName?.error= "User Name Muy Largo"
            false
        } else {
            textUserName?.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val pass: String = textPass?.editText?.text.toString().trim()
        val confirmPass: String = textConfirmPass?.editText?.text.toString().trim()

        return if (pass.isEmpty()) {
            textPass?.error = "Este Campo no puede estar Vacio"
            false
        } else if (confirmPass.isEmpty()) {
            textConfirmPass?.error = "Este Campo no puede estar Vacio"
            false
        }  else if (confirmPass != pass ) {
            //textPass?.error = "Las Contraseñas no Coinciden"
            textConfirmPass?.error = "Las Contraseñas no Coinciden"
            false
        } else {
            textPass?.error = null
            textConfirmPass?.error = null
            true
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al Registar el Usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(myEmail:String,provider:ProviderType){
        val homeIntent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("email", myEmail)
            Log.d("CORREO", myEmail)
        startActivity(homeIntent)
    }
}