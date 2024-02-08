package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.school_project_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userAccount.setOnClickListener { Utils.moveToUserPage(this) }

        binding.backBtn.setOnClickListener { finish() }

        binding.diaryBtn.setOnClickListener { Utils.moveToDiaryPage(this) }

    }
}