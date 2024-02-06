package com.example.school_project_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.school_project_1.databinding.ActivityAccountBinding
class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private val mAuth = Utils.mAuth
    private val mDbRef = Utils.mDbRef
    private val user = Utils.user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)

        if (Utils.signInCheck(user)){
            setContentView(binding.root)
        }else {
            // No user is signed in
            Utils.moveToLogInPage(this)
            return
        }

        user?.let {
            // Name
            val name = it.displayName
            Log.d(name,"test")
            binding.userProfileLabel.text = name
        }

        binding.slidersBtn.setOnClickListener {
            mAuth.signOut()
            Utils.moveToMainPage(this)
        }

        binding.backBtn.setOnClickListener {
            user?.let { it1 -> deleteUserDB(it1.uid) }
            user?.delete()
            Toast.makeText(this, "Successfully deleted your account", Toast.LENGTH_SHORT).show()
            Utils.moveToMainPage(this)
        }
    }
    private fun deleteUserDB(uId: String){
        mDbRef.child("user").child(uId).removeValue()
    }
}