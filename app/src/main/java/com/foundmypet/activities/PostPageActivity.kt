package com.foundmypet

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.foundmypet.Model.User
import com.foundmypet.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_post_page.*

class PostPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_page)

        //DATA INTENT - DATA EXTRA
        val postTittle = intent.getStringExtra("iPostTittle")
        val postImage = intent.getStringExtra("iPostImage")
        val postDate = intent.getStringExtra("iPostDate")
        val petName = intent.getStringExtra("iPetName")
        val petSpecies = intent.getStringExtra("iPetSpecies")
        val petRace = intent.getStringExtra("iPetRace")
        val petColor = intent.getStringExtra("iPetColor")
        val phoneNumber = intent.getStringExtra("iPhoneNumber")
        val postDescription = intent.getStringExtra("iPostDescription")

        val postPublisher:String = intent.getStringExtra("iPostPublisher").toString()

        text_title_looking_dog.text = postTittle
        Picasso.get().load(postImage).into(image_post_page)
        text_time_published_post_page.text = postDate
        text_name_post_page.text = petName
        text_species_post_page.text = petSpecies
        text_breed_post_page.text = petRace
        text_color_post_page.text = petColor
        text_number_contact_post_page.text = phoneNumber
        text_description_post_page.text = postDescription

        publisherInfo(postPublisher)
    }

    private fun publisherInfo(postPublisher: String) {
        val usersRef = FirebaseDatabase.getInstance().reference.child("Users").child(postPublisher)

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val user = snapshot.getValue<User>(User::class.java)
                    Picasso.get().load(user!!.getImage()).into(image_user_profile_post_page)
                    text_user_name_post_page.text = user.getUser()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}