package com.foundmypet

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.foundmypet.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_page.*

class PostPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_page)

        //DATA INTENT - DATA EXTRA
        val postTittle = intent.getStringExtra("iPostTittle")
        val postImage = intent.getStringExtra("iPostImage")
        val postUserImage = intent.getStringExtra("iPostUserImage")
        val postUserName = intent.getStringExtra("iPostUserName")
        val postDate = intent.getStringExtra("iPostDate")
        val petName = intent.getStringExtra("iPetName")
        val petSpecies = intent.getStringExtra("iPetSpecies")
        val petRace = intent.getStringExtra("iPetRace")
        val petColor = intent.getStringExtra("iPetColor")
        val phoneNumber = intent.getStringExtra("iPhoneNumber")
        val postDescription = intent.getStringExtra("iPostDescription")

        text_title_looking_dog.text = postTittle
        Picasso.get().load(postImage).into(image_post_page)
        text_time_published_post_page.text = postDate
        text_name_post_page.text = petName
        text_species_post_page.text = petSpecies
        text_breed_post_page.text = petRace
        text_color_post_page.text = petColor
        text_number_contact_post_page.text = phoneNumber
        text_description_post_page.text = postDescription

        Picasso.get().load(postUserImage).into(image_user_profile_post_page)
        text_user_name_post_page.text = postUserName
    }
}