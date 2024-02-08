package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.example.school_project_1.databinding.ActivityDiaryBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

class DiaryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiaryBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mStorageRef : StorageReference
    private var mainLayout = binding.linearLayout2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference
        mStorageRef = Firebase.storage.reference


//        val dir = filesDir.absolutePath //내부경로의 절대 경로
//        val filename = "파일이름.txt"
//        val contentes = "파일 내용\n"

        if (Utils.signInCheck(mAuth.currentUser)) {
            setContentView(binding.root)
        } else{
            Utils.moveToLogInPage(this)
        }

        val btnLayout = LinearLayout(this)
        val btnDate = Button(this)
        val btnContent = Button(this)

        btnLayout.setPadding(10)
        btnLayout.orientation = LinearLayout.HORIZONTAL
        btnLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            72
        )

        btnDate.setBackgroundResource(R.drawable.brown_btn)
        btnDate.gravity = Gravity.CENTER
        btnDate.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        btnLayout.addView(btnDate)
        mainLayout.addView(btnLayout)

        binding.backBtn.setOnClickListener { Utils.moveToMainPage(this) }

        //binding.slidersBtn.setOnClickListener { TODO }
    }
}