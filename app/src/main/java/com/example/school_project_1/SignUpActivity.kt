package com.example.school_project_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school_project_1.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = Firebase.auth
        val dbRef = FirebaseDatabase.getInstance().reference // Database realtime
        val message = binding.message
        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        binding.btnRegister.setOnClickListener {
            val pwd = binding.editPw.text.toString()
            val pwd2 = binding.editPw2.text.toString()
            val email = binding.editId.text.toString()
            signUpProcess(email, pwd, pwd2, pattern, message)
        }
    }
    private fun moveToMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun signUpProcess(email: String,pwd : String, pwd2:String, pattern: Pattern, message: TextView) {
        if (!pattern.matcher(email).matches()) {
            //Invalid Email
            message.text = "Invalid Email Address."
            return
        }

        if (pwd != pwd2){
            //Valid Email & Matching Passwords
            message.text = "Password mismatching."
            return
        }

        message.text = "Well Done!"
        mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener { task ->
            if (task.isSuccessful){
                //pass
                Toast.makeText(this,"Successfully signed up",Toast.LENGTH_SHORT).show()
                moveToMainPage()
            }else{
                Toast.makeText(this,"Failed to sign up",Toast.LENGTH_SHORT).show()
            }
        }
    }


}