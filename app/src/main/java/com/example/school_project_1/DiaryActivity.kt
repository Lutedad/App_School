package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference
        mStorageRef = Firebase.storage.reference

        if (Utils.signInCheck(mAuth.currentUser)) {
            setContentView(binding.root)
        } else {
            finish()
            Utils.moveToLogInPage(this)
        }

        // Create LinearLayout
        val linearLayout = LinearLayout(this)
        // Set padding on the LinearLayout
        linearLayout.setPadding(5, 5, 5, 5)
        linearLayout.setBackgroundResource(R.drawable.blue_btn)
        linearLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Create a View
        val view = View(this)
        val view1 = View(this)
        val view2 = View(this)

        // Set LayoutParams with weight for the View
        val params = LinearLayout.LayoutParams(
            0, // Width
            ViewGroup.LayoutParams.MATCH_PARENT,   // Height
            1.0f // Weight
        )

        // Create a Button
        val buttonDate = Button(this)
        buttonDate.setBackgroundResource(R.drawable.brown_btn)
        buttonDate.text = "06/27"

        // Set LayoutParams for the ButtonDate
        val paramsButtonDate = LinearLayout.LayoutParams(
            0, // Width
            ViewGroup.LayoutParams.WRAP_CONTENT,   // Height
            1.0f // Weight
        )
        buttonDate.layoutParams = paramsButtonDate

        // Create another Button
        val buttonText = Button(this)
        buttonText.setBackgroundResource(R.drawable.brown_btn)
        buttonText.text = "Hello"

        // Set LayoutParams for the ButtonText
        val paramsButtonText = LinearLayout.LayoutParams(
            0, // Width
            ViewGroup.LayoutParams.WRAP_CONTENT,   // Height
            4.0f // Weight
        )
        buttonText.layoutParams = paramsButtonText

        // Set LayoutParams for the View
        view.layoutParams = params
        view1.layoutParams = params
        params.weight = 0.5f
        view2.layoutParams = params

        // Add the View and Buttons to the LinearLayout
        linearLayout.addView(view)
        linearLayout.addView(buttonDate)
        linearLayout.addView(view2)
        linearLayout.addView(buttonText)
        linearLayout.addView(view1)

        // Add LinearLayout to ScrollView
        binding.scrollLinear.addView(linearLayout)

        binding.backBtn.setOnClickListener { Utils.moveToMainPage(this) }
    }
}
