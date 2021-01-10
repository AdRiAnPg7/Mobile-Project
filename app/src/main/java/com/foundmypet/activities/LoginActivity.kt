package com.foundmypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    // Vars Text Inputs
    private var textEmail: TextInputLayout? = null
    private var textPass: TextInputLayout? = null

    //Vars Auth
    private var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Redirect Register Activity
        val textGoToRegister = findViewById<TextView>(R.id.text_go_to_register)
        textGoToRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        // Init Vars Text Inputs
        textEmail = findViewById<TextInputLayout>(R.id.text_container_email_login)
        textPass = findViewById<TextInputLayout>(R.id.text_container_password)

        // Firebase
        mAuth = FirebaseAuth.getInstance()

        // Setup
        setup()
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

    private fun setup(){
        button_login.setOnClickListener {
            if( validateEmail() && validatePassword()){
                mAuth?.signInWithEmailAndPassword(
                    text_email.text.toString(),
                    text_password.text.toString())
                    ?.addOnCompleteListener (this) { task ->
                        if (task.isSuccessful){
                            showHome(task.result?.user?.email?:"Este Email No Esta Registrado", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }

                    }
            }
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput: String = textEmail?.editText?.text.toString().trim()
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

    private fun validatePassword(): Boolean {
        val pass: String = textPass?.getEditText()?.getText().toString().trim()

        return if (pass.isEmpty()) {
            textPass?.error = "Este Campo no puede estar Vacio"
            false
        } else {
            textPass?.error = null
            true
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

    private fun showHome(email:String,provider:ProviderType) {
        val homeIntent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("email", email)
            Log.d("CORREO", email)
        startActivity(homeIntent)
    }
}