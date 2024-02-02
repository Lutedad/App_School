package com.example.school_project_1

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val password = findViewById<EditText>(R.id.edit_pw)
        val passwordTwo = findViewById<EditText>(R.id.edit_pw_2)
        val email = findViewById<EditText>(R.id.edit_id)
        val message = findViewById<TextView>(R.id.message)
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        val signUpBtn = findViewById<Button>(R.id.btn_register)

        fun moveToMainPage(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        fun signUpProcess(){
            if (pattern.matcher(email.text).matches()) {
                //Valid Email
                if (password.text.toString() == passwordTwo.text.toString()){
                    //Valid Email & Matching Passwords
                    message.text = "Well Done!"
                    moveToMainPage()
                } else {
                    message.text = "Password mismatching."
                }
            } else {
                //Invalid Email
                message.text = "Invalid Email Address."
            }
        }

        signUpBtn.setOnClickListener {
            signUpProcess()
        }


    }

}