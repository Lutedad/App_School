package com.example.school_project_1

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school_project_1.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = Firebase.auth // reset auth
        mDbRef = Firebase.database.reference // reset Db

        val pattern: Pattern = Patterns.EMAIL_ADDRESS


        binding.btnRegister.setOnClickListener {
            val pwd = binding.editPw.text.toString()
            val pwd2 = binding.editPw2.text.toString()
            val email = binding.editId.text.toString()
            val name = binding.editName.text.toString()
            val message: TextView = binding.message
            signUpProcess(email, pwd, pwd2, name, pattern, message)
        }
    }
    private fun moveToMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun signUpProcess(email: String,pwd : String, pwd2:String, name: String, pattern: Pattern, message: TextView) {
        if (!pattern.matcher(email).matches()) {
            //Invalid Email
            message.text = "Invalid Email Address"
            return
        }

        if (pwd != pwd2 || pwd.length < 6){
            //Valid Email & Matching Passwords return
            message.text = "Invalid Password. It has to be at least 6 digits."
            return
        }


        //mDbRef.child('user').addValueEventListener(object :ValueEventListener{})
        //for (postSnapshot in snapshot.children)
        mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener { task ->
            if (task.isSuccessful){
                //pass
                Toast.makeText(this,"Successfully signed up",Toast.LENGTH_SHORT).show()
                moveToMainPage()
                addUserToDB(name, email, mAuth.currentUser?.uid!!)
            }else{
                try {
                    throw task.exception!!
                } catch (e: FirebaseAuthUserCollisionException) {
                    // email already in use
                    Toast.makeText(applicationContext, "Email already taken!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addUserToDB(name:String, email:String, uId: String){
        mDbRef.child("user").child(uId).setValue(User(name, email, uId))
    }

}