package com.example.sl3verbeterd.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubDatabase
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.R
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import com.example.sl3verbeterd.ui.profile.ProfileActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class LoginActivity : AppCompatActivity() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var hireHubDao: HireHubDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hireHubDao = HireHubDatabase.getDatabase(this, applicationScope).hireHubDao()

        val loginButton = findViewById<Button>(R.id.login_button)
        val editTextLoginUser = findViewById<EditText>(R.id.editTextLoginUser)
        val editTextLoginPassword = findViewById<EditText>(R.id.editTextLoginPassword)
        val registerButton = findViewById<Button>(R.id.register_button)
        val guestButton = findViewById<Button>(R.id.guest_button)

        loginButton.setOnClickListener {
            val username = editTextLoginUser.text.toString()
            val password = editTextLoginPassword.text.toString()

            applicationScope.launch {
                val accountState = authenticateUserLocally(username, password)

                withContext(Dispatchers.Main) {
                    if (accountState.isAuthenticated) {
                        showLoginSuccessDialog()

                        val user = hireHubDao.getUserByUsername(username)
                        val userProfile = hireHubDao.getProfileById(user.id) // Fetch user's profile from the database

                        startNextActivity(accountState, userProfile)
                    } else {
                        showLoginFailureDialog()
                    }
                }
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        guestButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            // Set the user role as "guest" to indicate guest user
            intent.putExtra("role", "guest")
            startActivity(intent)
            finish() // Finish LoginActivity to prevent going back on continuing as a guest
        }
    }

    private suspend fun authenticateUserLocally(username: String, password: String): AccountState {
        return withContext(IO) {
            val user = hireHubDao.getUserByUsername(username)
            if (user != null && user.password == password) {
                AccountState(true, user.role)
            } else {
                AccountState(false, "guest") // Default role for unauthenticated users
            }
        }
    }

    private fun startNextActivity(accountState: AccountState, userProfile: UserProfile?) {
        val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
        userProfile?.let {
            intent.putExtra("firstName", it.firstName)
            intent.putExtra("lastName", it.lastName)
            intent.putExtra("location", it.location)
            intent.putExtra("job", it.job)
        }
        startActivity(intent)
        finish() // Finish LoginActivity to prevent going back on successful login
    }

    private fun showLoginSuccessDialog() {
        val dialogFragment = CustomDialogFragment()
        dialogFragment.show(supportFragmentManager, "CustomDialogFragment")
    }

    private fun showLoginFailureDialog() {
        val builder = AlertDialog.Builder(this)
        val dialog = builder.setTitle("Login Failed")
            .setMessage("Incorrect credentials. Please try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()

        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()
        }, 3000) // 3000 milliseconds (3 seconds) delay before dismissing the dialog
    }
}
