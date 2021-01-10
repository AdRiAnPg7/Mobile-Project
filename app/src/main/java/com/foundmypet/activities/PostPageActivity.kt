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

        /*val image = BitmapFactory.decodeResource(resources, R.drawable.fotopersonacomun)
        val round = RoundedBitmapDrawableFactory.create(resources, image)

//        val image2 = BitmapFactory.decodeResource(resources, R.drawable.perro_surf)
//        val round2 = RoundedBitmapDrawableFactory.create(resources, image)

        round.isCircular = true
        image_user_profile_post_page.setImageDrawable(round)*/


//
//        round2.cornerRadius = 50f
//        image_user_profile_post_page.setImageDrawable(round2)

        //obtencion de datos de PostListAdapter
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

        text_user_name_post_page.text = postUserName
        text_time_published_post_page.text = postDate
        text_description_post_page.text = postDescription
        Picasso.get().load(postImage).into(image_post_page)

        //imagen usuario
        Picasso.get().load(postUserImage).into(image_user_profile_post_page)
    }
}