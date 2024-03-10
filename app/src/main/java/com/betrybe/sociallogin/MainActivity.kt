package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
            val password = passwordEditText?.text.toString() ?: ""
            val email = it?.toString() ?: ""
            loginButton.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }

        passwordEditText?.addTextChangedListener {
            val email = emailEditText?.text.toString() ?: ""
            val password = it?.toString() ?: ""
            loginButton.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }
}
