package com.example.school_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.btn_login)
        val signupButton = findViewById<Button>(R.id.btn_register)
        val userID = findViewById<EditText>(R.id.edit_id)
        val userPW = findViewById<EditText>(R.id.edit_pw)

        fun moveToMainPage(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fun moveToSignUpPage(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            Log.d((userID.text.toString()),"잘되네")
            moveToMainPage()
        }

        signupButton.setOnClickListener {
            moveToSignUpPage()
        }
    }
}