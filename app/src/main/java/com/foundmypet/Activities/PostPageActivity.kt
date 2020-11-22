package com.foundmypet

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.foundmypet.R
import kotlinx.android.synthetic.main.activity_post_page.*

class PostPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_page)

        val image = BitmapFactory.decodeResource(resources, R.drawable.fotopersonacomun)
        val round = RoundedBitmapDrawableFactory.create(resources, image)

//        val image2 = BitmapFactory.decodeResource(resources, R.drawable.perro_surf)
//        val round2 = RoundedBitmapDrawableFactory.create(resources, image)

        round.isCircular = true
        image_user_profile_post_page.setImageDrawable(round)


//
//        round2.cornerRadius = 50f
//        image_user_profile_post_page.setImageDrawable(round2)
    }
}