package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep((2000))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTheme(R.style.AppTheme)

        val buttonLogin = findViewById<Button>(R.id.buttonGoToLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonGoToRegister)

        buttonLogin.setOnClickListener{
            startActivity(Intent(this,activity_login::class.java))
        }

        buttonRegister.setOnClickListener{
            startActivity(Intent(this,register::class.java))
        }


    }
}