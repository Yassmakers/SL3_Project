package com.example.sl3verbeterd.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubDatabase
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.R
import com.example.sl3verbeterd.ui.applicant.UpdateApplicantsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class LoginActivity : AppCompatActivity()  {
    private lateinit var hireHubDao: HireHubDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hireHubDao = HireHubDatabase.getInstance(this).hireHubDao()

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            val editTextLoginUser = findViewById<EditText>(R.id.editTextLoginUser)
            val username = editTextLoginUser.text.toString()
            val editTextLoginPassword = findViewById<EditText>(R.id.editTextLoginPassword)
            val password = editTextLoginPassword.text.toString()


                val user = hireHubDao.getUserByUsername(username)
                if (user != null && user.password == password) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@LoginActivity, LandingActivity::class.java)
                    startActivity(intent)
                }

        }
    }
}