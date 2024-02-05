package com.example.school_project_1

import android.content.Context
import android.content.Intent

class Utils {
    companion object {
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
    }
}
