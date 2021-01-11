package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.lang.StringBuilder
import android.widget.ImageButton
import com.foundmypet.Model.User
import com.foundmypet.activities.EditProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_user_profile.image_user_profile

class UserProfileActivity : AppCompatActivity() {

    // User vars
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // user
        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        // get Data
        getUserData()

        // routing
        val editProfile = findViewById<ImageButton>(R.id.button_confirm_profile_edit)

        editProfile.setOnClickListener{
            startActivity(Intent(this,EditProfileActivity::class.java))
        }

        val exitProfile = findViewById<ImageButton>(R.id.button_cancel_edit_profile)

        exitProfile.setOnClickListener{
            this.finish()
        }

    }

    private fun getUserData() {
        val currentUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser.uid)
        currentUser.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   val user = snapshot.getValue<User>(User::class.java)
                   Picasso.get().load(user!!.getImage()).into(image_user_profile)
                   profile_user_name.text = user!!.getUser()
                   email_user.text = user!!.getEmail()
                   cellphone_user.text = user!!.getCellphone()
                   profile_user_department.text = user!!.getCity()
                   profile_user_country.text = user!!.getCountry()
               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}
