package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.school_project_1.databinding.ActivityDiaryBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class DiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiaryBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference

        if (Utils.signInCheck(mAuth.currentUser)) {
            setContentView(binding.root)
        } else{
            Utils.moveToLogInPage(this)
        }

        mAuth.currentUser?.let {
            // Name
            val name = it.displayName
        }

    }
}