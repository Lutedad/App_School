import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.school_project_1.AccountActivity
import com.example.school_project_1.DiaryActivity
import com.example.school_project_1.DiaryManager
import com.example.school_project_1.DiaryWriteActivity
import com.example.school_project_1.LoginActivity
import com.example.school_project_1.MainActivity
import com.example.school_project_1.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class Utils {
    companion object {
        private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

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

        fun moveToDiaryWritePage(context: Context) {
            val intent = Intent(context, DiaryWriteActivity::class.java)
            context.startActivity(intent)
        }

        fun signInCheck(user: FirebaseUser?): Boolean {
            return user != null
        }

        fun updateUser(name: String) {
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }

            mAuth.currentUser?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Update successful
                    } else {
                        // Handle the error, log or notify the user
                    }
                }
        }

        fun sendVerificationEmail(context: Context): Boolean {
            return try {
                mAuth.currentUser?.sendEmailVerification()
                true
            } catch (e: Exception) {
                false
            }
        }

        fun readTxT(fullPath: String): String {
            try {
                val file = File(fullPath)

                if (!file.exists()) {
                    return ""
                }

                val reader = FileReader(file)
                val buffer = BufferedReader(reader)

                var temp: String?
                val result = StringBuffer()

                while (true) {
                    temp = buffer.readLine()
                    if (temp == null) break
                    else result.append(temp).append("\n")
                }

                buffer.close()
                return result.toString()
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle the exception (log or notify the user)
                return ""
            }
        }
    }
}
