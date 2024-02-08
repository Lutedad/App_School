package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.school_project_1.databinding.ActivityAccountBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference
        val user = mAuth.currentUser

        if (Utils.signInCheck(user)){
            setContentView(binding.root)
        }else {
            // No user is signed in
            Utils.moveToLogInPage(this)
            finish()
            return
        }

        user?.let {
            // Name
            val name = it.displayName
            Log.d(name,"test")
            binding.title.text = name
        }

        binding.slidersBtn.setOnClickListener {
            mAuth.signOut()
            Utils.moveToMainPage(this)
        }

        binding.backBtn.setOnClickListener {
            Utils.moveToMainPage(this)
        }
    }
    private fun deleteUserDB(uId: String){
        mDbRef.child("user").child(uId).removeValue()
    }
}

//            user?.let { it1 -> deleteUserDB(it1.uid) }
//            user?.delete()
//            Toast.makeText(this, "Successfully deleted your account", Toast.LENGTH_SHORT).show()
//            Utils.moveToMainPage(this)