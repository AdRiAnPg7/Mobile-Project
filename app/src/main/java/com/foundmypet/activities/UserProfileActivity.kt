package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.foundmypet.activities.EditProfileActivity

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val editProfile = findViewById<ImageButton>(R.id.button_edit_profile)

        editProfile.setOnClickListener{
            startActivity(Intent(this,EditProfileActivity::class.java))
        }

        val exitProfile = findViewById<ImageButton>(R.id.button_exit_profile)

        exitProfile.setOnClickListener{
            this.finish()
        }

    }
}