package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.school_project_1.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountButton = binding.userAccount
        val backButton = binding.backBtn
        val snsButton = binding.twitterBtn

        accountButton.setOnClickListener {
            Utils.moveToUserPage(this)
        }

        backButton.setOnClickListener {
            simulateBackspace()
        }

    }
    private fun simulateBackspace() {
        KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
    }

}