package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class activity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textGoToRegister = findViewById<TextView>(R.id.textRegister)

        textGoToRegister.setOnClickListener{
            startActivity(Intent(this,register::class.java))
        }


    }
}