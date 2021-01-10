package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.lang.StringBuilder
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

    var getData = object :ValueEventListener{
        override fun onCancelled(error: DatabaseError) {
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            var currentUserName = StringBuilder()
            for(i in snapshot.children)
            {
                var name1 = i.child("Users").getValue()
                currentUserName = name1 as StringBuilder
            }
            header_username_text.text = currentUserName.toString()
        }
    }
}
