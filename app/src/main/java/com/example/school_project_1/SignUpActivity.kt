package com.example.school_project_1

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
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference

        if (Utils.signInCheck(mAuth.currentUser)){
            //user is signed in
            Utils.moveToUserPage(this)
        }else {
            setContentView(binding.root)
        }

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
    private fun signUpProcess(email: String,pwd : String, pwd2:String, name: String, pattern: Pattern, message: TextView) {

        if (!pattern.matcher(email).matches()) {
            message.text = "Invalid Email Address"
            return
        }

        if (pwd != pwd2 || pwd.length < 6){
            message.text = "Invalid Password. It has to be at least 6 digits."
            return
        }


        //mDbRef.child('user').addValueEventListener(object :ValueEventListener{})
        //for (postSnapshot in snapshot.children)
        mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener { task ->
            if (task.isSuccessful){
                userSuccessfullyCreated(name,email)
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
    private fun userSuccessfullyCreated(name: String, email: String){
        updateUser(name)
        addUserToDB(name, email, mAuth.currentUser?.uid!!)
        sendVerificationEmail()
        Utils.moveToMainPage(this)
    }
    private fun sendVerificationEmail(){
        try {
            mAuth.currentUser!!.sendEmailVerification()
            Toast.makeText(this, "Verification email sent.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to Send a Verification Email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUser(name:String){
        val profileUpdates = userProfileChangeRequest {
            displayName = name
            //photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
        }

        mAuth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                }
            }
    }

    private fun addUserToDB(name:String, email:String, uId: String){
        mDbRef.child("user").child(uId).setValue(User(name, email, uId))
    }

}