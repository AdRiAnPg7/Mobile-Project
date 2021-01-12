package com.foundmypet

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_create_lost_pet_post.*

class CreateLostPetPostActivity : AppCompatActivity() {
    private var myUrl = ""
    private var imageUri:Uri? = null
    private var storagePostPicRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_lost_pet_post)

        storagePostPicRef = FirebaseStorage.getInstance().reference.child("Posts Pictures")
        btn_create_post.setOnClickListener { uploadImage() }
        CropImage.activity()
            .setAspectRatio(4,3)
            .start(this@CreateLostPetPostActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val result = CropImage.getActivityResult(data)
            imageUri = result.uri
            image_create_lost_pet_post.setImageURI(imageUri)
        }
    }

    private fun uploadImage() {
        when{
            imageUri == null -> Toast.makeText(this,"Please select image first.",Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(text_input_lost_pet_post_tittle.text.toString()) -> Toast.makeText(this, "Please write a title for the Post", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(input_create_lost_pet_post_name.text.toString()) -> Toast.makeText(this, "Please write the name of the pet for the Post", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(input_create_lost_pet_post_species.text.toString()) -> Toast.makeText(this, "Please write the species for the Post", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(input_create_lost_pet_post_race.text.toString()) -> Toast.makeText(this, "Please write the race for the Post", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(input_create_lost_pet_post_color.text.toString()) -> Toast.makeText(this, "Please write the color for the Post", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(input_create_lost_pet_post_lastSeen.text.toString()) -> Toast.makeText(this, "Please write the date for the Post", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(input_create_lost_pet_post_phoneNumber.text.toString()) -> Toast.makeText(this, "Please write the phone number for the Post", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(input_create_lost_pet_post_descripion.text.toString()) -> Toast.makeText(this, "Please write the description for the Post", Toast.LENGTH_SHORT).show()

            else -> {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Publicando")
                progressDialog.setMessage("Espere un momento por favor")
                progressDialog.show()

                val fileRef = storagePostPicRef!!.child(System.currentTimeMillis().toString() + ".jpg")

                var uploadTask:StorageTask<*>
                uploadTask = fileRef.putFile(imageUri!!)

                uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful)
                    {
                        task.exception?.let {
                            throw it
                            progressDialog.dismiss()
                        }
                    }
                    return@Continuation fileRef.downloadUrl
                }).addOnCompleteListener (OnCompleteListener<Uri> { task ->
                    if (task.isSuccessful) {
                        val dowloadUrl = task.result
                        myUrl = dowloadUrl.toString()

                        val ref = FirebaseDatabase.getInstance().reference.child("Posts")
                        val postId = ref.push().key

                        val postMap = HashMap<String, Any>()
                        postMap["postId"] = postId!!
                        postMap["title"] = text_input_lost_pet_post_tittle.text.toString()
                        postMap["postImage"] = myUrl
                        postMap["postDate"] = input_create_lost_pet_post_lastSeen.text.toString()
                        postMap["petName"] = input_create_lost_pet_post_name.text.toString()
                        postMap["petSpecies"] = input_create_lost_pet_post_species.text.toString()
                        postMap["petRace"] = input_create_lost_pet_post_race.text.toString()
                        postMap["petColor"] = input_create_lost_pet_post_color.text.toString()
                        postMap["phoneNumber"] = input_create_lost_pet_post_phoneNumber.text.toString()
                        postMap["postDescription"] = input_create_lost_pet_post_descripion.text.toString()
                        postMap["postPublisher"] = FirebaseAuth.getInstance().currentUser!!.uid
                        ref.child(postId).updateChildren(postMap)

                        Toast.makeText(this,"Post uploaded successfully", Toast.LENGTH_LONG).show()

                        val intent = Intent (this@CreateLostPetPostActivity, HomePageActivity::class.java)
                        startActivity(intent)
                        finish()

                        progressDialog.dismiss()
                    }
                    else{
                        progressDialog.dismiss()
                    }
                } )
            }
        }
    }
}

