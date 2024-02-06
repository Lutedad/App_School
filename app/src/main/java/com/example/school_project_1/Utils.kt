package com.example.school_project_1

import android.content.Context
import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class Utils {
    companion object {

        val mAuth: FirebaseAuth = Firebase.auth
        val mDbRef: DatabaseReference = Firebase.database.reference
        var user: FirebaseUser? = mAuth.currentUser
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
        //TODO IT RELOADS USER DATA IN CASE USER SIGN IN WITH DIFFERENT ACCOUNT
//        fun reloadUserData(){
//            var user: FirebaseUser? = mAuth.currentUser
//        }
        fun signInCheck(user: FirebaseUser?): Boolean {
            //if user is signed in return True
            return user != null
        }
    }
}
