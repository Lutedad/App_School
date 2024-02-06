package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.school_project_1.databinding.ActivityDiaryBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class DiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiaryBinding
    private val mAuth = Utils.mAuth
    private val mDbRef = Utils.mDbRef
    private val user = Utils.user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)

        if (Utils.signInCheck(user)) {
            setContentView(binding.root)
        } else{
            Utils.moveToLogInPage(this)
        }

        user?.let {
            // Name
            val name = it.displayName
        }

    }
}