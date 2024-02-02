package com.example.school_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        fun moveToAnotherPage(){
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            moveToAnotherPage()
        }
    }
}