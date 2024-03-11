package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    // declarando as variaves que receberam os atributos do layout
    private val emailTextInputLayout by lazy { findViewById<TextInputLayout>(R.id.email_text_input_layout) }
    private val passwordTextInputLayout by lazy { findViewById<TextInputLayout>(R.id.password_text_input_layout) }
    private val loginButton by lazy { findViewById<Button>(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = emailTextInputLayout.editText
        val passwordEditText = passwordTextInputLayout.editText

        // configura o listener de mudança de texto para o campo email
        emailEditText?.addTextChangedListener {
            val passwordText = passwordEditText?.text.toString()
            val email = it?.toString() ?: ""
            loginButton.isEnabled = email.isNotEmpty() && passwordText.isNotEmpty()
        }

        // configura o listener de mudança de texto para o campo senha
        passwordEditText?.addTextChangedListener {
            val emailText = emailEditText?.text.toString()
            val password = it?.toString() ?: ""
            loginButton.isEnabled = emailText.isNotEmpty() && password.isNotEmpty()
        }

        // configura o listener de clique para o botao de entrar
        loginButton.setOnClickListener {
           val email = emailEditText?.text.toString()
           val password = passwordEditText?.text.toString()

           if(validateEmail(email) && password.length > 4) {
               emailTextInputLayout.error = null
               passwordTextInputLayout.error = null

               Toast.makeText(
                   this,
                   R.string.login_succeeded,
                   Toast.LENGTH_LONG
               ).show()
           } else{
               if (password.length <= 4) {
                   passwordTextInputLayout.error = getString(R.string.password_warning)
               } else {
                   emailTextInputLayout.error = getString(R.string.email_warning)
               }
           }
        }
    }

    private fun validateEmail(email:String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}
