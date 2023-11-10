package com.example.sl3verbeterd.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.R
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory

class RegisterActivity : AppCompatActivity() {


    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_register)

        // Account
        val addUserName = findViewById<EditText>(R.id.user_name)
        val addPassWord = findViewById<EditText>(R.id.password)

        // Profile
        val addFirstName = findViewById<EditText>(R.id.first_name)
        val addLastName = findViewById<EditText>(R.id.last_name)
        val addLocation = findViewById<EditText>(R.id.location)
        val addEducation = findViewById<EditText>(R.id.education)
        val addJob = findViewById<EditText>(R.id.job)


        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            // Profile
            val firstName = addFirstName.text.toString()
            val lastName = addLastName.text.toString()
            val location = addLocation.text.toString()
            val job = addJob.text.toString()
            val education = addEducation.text.toString()

            // Account
            val userName = addUserName.text.toString()
            val passWord = addPassWord.text.toString()
            val role = "user"
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val profile = Profile(
                    firstName = firstName,
                    lastName = lastName,
                    location = location,
                    job = job,
                    education = education,
                    visibility = true
                )
                applicantsViewModel.insertProfile(profile)
                val account = Account(username = userName, password = passWord, role = role)
                applicantsViewModel.insertAccount(account)
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}