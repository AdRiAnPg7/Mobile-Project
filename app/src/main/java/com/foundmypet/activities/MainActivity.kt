package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTheme(R.style.AppTheme)

        val buttonLogin = findViewById<Button>(R.id.button_go_to_login)
        val buttonRegister = findViewById<Button>(R.id.button_go_to_register)

        buttonLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        buttonRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }


    }

    override fun onStart() {
        super.onStart()

        if(FirebaseAuth.getInstance().currentUser !=null){

            val intent =  Intent(this, HomePageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(intent)
            finish()
        }
    }


}