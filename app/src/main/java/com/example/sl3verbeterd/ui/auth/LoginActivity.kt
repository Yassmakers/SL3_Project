package com.example.sl3verbeterd.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubDatabase
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.R
import androidx.lifecycle.viewModelScope
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory

class LoginActivity : AppCompatActivity()  {

    private lateinit var hireHubDao: HireHubDao
    private val applicationScope = (application as HireHubApplication).applicationScope


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hireHubDao = HireHubDatabase.getDatabase(this, applicationScope).hireHubDao()

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            val editTextLoginUser = findViewById<EditText>(R.id.editTextLoginUser)
            val username = editTextLoginUser.text.toString()
            val editTextLoginPassword = findViewById<EditText>(R.id.editTextLoginPassword)
            val password = editTextLoginPassword.text.toString()

//
//            viewModelScope.launch {
//                val user = hireHubDao.getUserByUsername(username)
//                if (user != null && user.password == password) {
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    val intent = Intent(this@LoginActivity, LandingActivity::class.java)
//                    startActivity(intent)
//                }
//            }
        }

        }
    }
