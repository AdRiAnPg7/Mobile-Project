package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_register.*

class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()
    }

    private fun setup(){
        title = "Autentificacion"

        buttonConfirm.setOnClickListener {
            if(textNewEmail.text.isNotEmpty() && textNewPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    textNewEmail.text.toString(),
                    textNewPassword.text.toString())
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