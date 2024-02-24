package com.example.school_project_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.school_project_1.databinding.ActivityDiaryWriteBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.io.IOException

class DiaryWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryWriteBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDiaryWriteBinding.inflate(layoutInflater)
        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference


        setContentView(binding.root)

        val uId = mAuth.currentUser?.uid!!
        // Move these lines inside onCreate, after setting content view
        val filename = binding.title.text.toString()

        binding.saveBtn.setOnClickListener {

            val content = binding.diaryInput.text.toString()
            writeDiaryDB(filename, content, this, uId) }

        binding.backBtn.setOnClickListener { Utils.moveToDiaryPage(this) }

    }
    private fun writeDiaryDB(filename: String, content: String, context: Context, uId: String) {
        try {
            mDbRef.child("user").child(uId).child("diaries").child(filename).setValue(DiaryManager(content))
            Toast.makeText(context, "Successfully Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle the exception (log or notify the user)
        }
    }
}
