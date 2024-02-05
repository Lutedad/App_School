package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.school_project_1.databinding.ActivityAccountBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)

        val user = Firebase.auth.currentUser
        mDbRef = Firebase.database.reference // reset Db

        if (user != null) {
            // User is signed in
            setContentView(binding.root)

            user.let {
                // Name, email address, and profile photo Url
                val name = it.displayName
                Log.d(name,"test")
                binding.userProfileLabel.text = name
                //val photoUrl = it.photoUrl

                // Check if user's email is verified
                //val emailVerified = it.isEmailVerified

                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getIdToken() instead.
                //val uid = it.uid
            }
        } else {
            // No user is signed in
            Utils.moveToLogInPage(this)
            return
        }

        binding.slidersBtn.setOnClickListener {
            Firebase.auth.signOut()
            Utils.moveToMainPage(this)
        }

        binding.backBtn.setOnClickListener {
            Firebase.auth.currentUser?.let { it1 -> deleteUserDB(it1.uid) }
            Firebase.auth.currentUser?.delete()
            Toast.makeText(this, "Successfully deleted your account", Toast.LENGTH_SHORT).show()
            Utils.moveToMainPage(this)
        }
    }
    private fun deleteUserDB(uId: String){
        mDbRef.child("user").child(uId).removeValue()
    }
}