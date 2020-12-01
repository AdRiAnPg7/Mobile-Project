package com.foundmypet

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    // Vars Show Pass
    private var isShowPass = false
    private  var isShowPassConfirm = false

    // Vars Text Inputs
    private var textEmail: TextInputLayout? = null
    private var textUserName: TextInputLayout? = null
    private var textPass: TextInputLayout? = null
    private var textConfirmPass: TextInputLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Init Vars Text Inputs
        textEmail = findViewById<TextInputLayout>(R.id.text_container_email)
        textPass = findViewById<TextInputLayout>(R.id.text_container_new_password)
        textConfirmPass = findViewById<TextInputLayout>(R.id.text_container_new_confirm_password)
        textUserName = findViewById<TextInputLayout>(R.id.text_container_user_name)

        // Setup
        setup()
    }


    private fun setup(){
        button_login_confirm.setOnClickListener {
            if( validateEmail() && validatePassword() && validateUserName()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    text_new_email.text.toString(),
                    text_new_password.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email?:"Este Email No existe", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }

                    }
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput: String = textEmail?.getEditText()?.getText().toString().trim()
        return if (emailInput.isEmpty()) {
            textEmail?.error = "Este Campo no puede estar Vacio"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textEmail?.error = "Ingrese un Correo Valido"
            false
        } else {
            textEmail?.error = null
            true
        }
    }

    private fun validateUserName(): Boolean {
        val usernameInput: String = textUserName?.getEditText()?.getText().toString().trim()
        return if (usernameInput.isEmpty()) {
            textUserName?.error= "Este Campo no puede estar Vacio"
            false
        } else if (usernameInput.length > 15) {
            textUserName?.error= "User Name Muy Largo"
            false
        } else {
            textUserName?.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val pass: String = textPass?.getEditText()?.getText().toString().trim()
        val confirmPass: String = textConfirmPass?.getEditText()?.getText().toString().trim()

        return if (pass.isEmpty()) {
            textPass?.error = "Este Campo no puede estar Vacio"
            false
        } else if (confirmPass.isEmpty()) {
            textConfirmPass?.error = "Este Campo no puede estar Vacio"
            false
        }  else if (confirmPass != pass ) {
            //textPass?.error = "Las Contraseñas no Coinciden"
            textConfirmPass?.error = "Las Contraseñas no Coinciden"
            false
        } else {
            textPass?.error = null
            textConfirmPass?.error = null
            true
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al Registar el Usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String,provider:ProviderType){
        val homeIntent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("email", email)

        startActivity(homeIntent)
    }
}