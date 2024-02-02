package com.example.school_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.school_project_1.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var mAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding.btnLogin.setOnClickListener {
            val userID = binding.editId.text.toString().trim()
            val userPW = binding.editPw.text.toString().trim()
            moveToMainPage()
        }

        binding.btnRegister.setOnClickListener {
            moveToSignUpPage()
        }
    }
    private fun moveToMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun moveToSignUpPage(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun login(email : String, pwd:String){

    }

}