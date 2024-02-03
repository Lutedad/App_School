package com.example.school_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.school_project_1.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var mAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val userID = if (binding.editId.text.toString().isNotEmpty()) {
                binding.editId.text.toString().trim()
            } else{
                "Empty"
            }
            val userPW = if (binding.editPw.text.toString().isNotEmpty()) {
                binding.editPw.text.toString().trim()
            }else{
                "Empty"
            }

            if (userID == "Empty" || userPW == "Empty") {
                Toast.makeText(this, "Please type your proper ID and Password.", Toast.LENGTH_SHORT).show()
            }else{
                login(userID,userPW)
                moveToMainPage()
            }

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
        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Well done BOY", Toast.LENGTH_SHORT).show()
                moveToMainPage()
                finish()
            }else{
                Toast.makeText(this, "EW...", Toast.LENGTH_SHORT).show()
                Log.d("Login","Error: ${task.exception}")
            }
        }
    }

}