package com.example.school_project_1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school_project_1.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var mAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val user = Firebase.auth.currentUser

        if (user != null) {
            // User is signed in
            Utils.moveToUserPage(this)
        } else {
            // No user is signed in
            setContentView(binding.root)
        }

        binding.btnLogin.setOnClickListener {
            val userID = if (binding.editId.text.isNotEmpty()) {
                binding.editId.text.toString().trim()
            } else {
                Toast.makeText(this, "Please enter your ID.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userPW = if (binding.editPw.text.isNotEmpty()) {
                binding.editPw.text.toString().trim()
            } else {
                Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            login(userID, userPW)
        }

        binding.btnRegister.setOnClickListener { Utils.moveToSignUpPage(this) }

        binding.backBtn.setOnClickListener { Utils.moveToMainPage(this) }
    }

    private fun login(email : String, pwd:String){
        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()
                Utils.moveToUserPage(this)
            }else{
                Toast.makeText(this, "Failed to log in", Toast.LENGTH_SHORT).show()
                Log.d("Login","Error: ${task.exception}")
            }
        }
    }
    private fun signOut() { mAuth.signOut() }
    private fun revokeAccess(){ mAuth.currentUser?.delete() }
}