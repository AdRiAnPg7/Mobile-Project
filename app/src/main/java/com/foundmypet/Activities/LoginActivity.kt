package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Redirect Register Activity
        val textGoToRegister = findViewById<TextView>(R.id.text_go_to_register)
        textGoToRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        // Setup
        setup()
    }

    private fun setup(){
        button_login.setOnClickListener {
            if(text_email.text.isNotEmpty() && text_password.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                    text_email.text.toString(),
                    text_password.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?:"Este Email No Esta Registrado", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }

                    }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El Correo o Contraseña es Incorrecta")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String,provider:ProviderType){
        val homeIntent = Intent(this, HomePageActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

}