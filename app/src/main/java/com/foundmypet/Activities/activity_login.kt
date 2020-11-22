package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class activity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textGoToRegister = findViewById<TextView>(R.id.text_go_to_register)

        textGoToRegister.setOnClickListener{
            startActivity(Intent(this,activity_register::class.java))
        }
        //setup
        setup()
    }

    private fun setup(){
        title = "Autentificacion"


        button_login.setOnClickListener {
            if(text_email.text.isNotEmpty() && text_password.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                    text_email.text.toString(),
                    text_password.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?:"Este Email No existe :v", ProviderType.BASIC)
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
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String,provider:ProviderType){
        val homeIntent = Intent(this, activity_home_page::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

}