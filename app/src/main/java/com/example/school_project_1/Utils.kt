package com.example.school_project_1

import android.content.Context
import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class Utils {
    companion object {

        private val mAuth: FirebaseAuth = Firebase.auth
        val mDbRef: DatabaseReference = Firebase.database.reference

        fun moveToLogInPage(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }

        fun moveToSignUpPage(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }

        fun moveToMainPage(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        fun moveToUserPage(context: Context) {
            val intent = Intent(context, AccountActivity::class.java)
            context.startActivity(intent)
        }

        fun moveToDiaryPage(context: Context) {
            val intent = Intent(context, DiaryActivity::class.java)
            context.startActivity(intent)
        }

        fun signInCheck(user: FirebaseUser?): Boolean {
            //if user is signed in return True
            return user != null
        }

        fun updateUser(name: String) {
            val profileUpdates = userProfileChangeRequest {
                displayName = name
                //photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
            }

            mAuth.currentUser!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    }
                }
        }

        fun sendVerificationEmail(context: Context): Boolean {
            return try {
                mAuth.currentUser!!.sendEmailVerification()
                true
            } catch (e: Exception) {
                false
            }
        }

        fun writeTxT(directory: String, filename: String, content: String) {
            val dir = File(directory)

            if (!dir.exists()) { dir.mkdirs() }

            val writer = FileWriter("$directory/$filename")

            val buffer = BufferedWriter(writer)
            buffer.write(content)
            buffer.close()
        }
        fun readTxT(fullPath: String): String {
            val file = File(fullPath)

            if (!file.exists()){
                return ""
            }
            val reader = FileReader(file)
            val buffer = BufferedReader(reader)

            var temp : String? = ""
            var result = StringBuffer()

            while (true){
                temp = buffer.readLine()
                if (temp==null) break
                else result.append(temp).append("\n")
            }

            buffer.close()
            return result.toString()
        }
    }
}
