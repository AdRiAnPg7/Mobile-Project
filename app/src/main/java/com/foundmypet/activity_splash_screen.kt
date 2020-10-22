package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screem.*

class activity_splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screem)
        val animation = AnimationUtils.loadAnimation(baseContext, R.anim.animation_rotate)
        imageLogoSplash.startAnimation(animation)

        goToMain()

    }
    private fun goToMain(){

        Handler().postDelayed({

            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 3400)

    }
}