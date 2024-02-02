package com.example.school_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonTwo = findViewById<Button>(R.id.button2)

        fun moveToAnotherPage(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonTwo.setOnClickListener {
            moveToAnotherPage()
        }
    }
}