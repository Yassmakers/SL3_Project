package com.example.sl3verbeterd.ui.applicant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
class NewApplicantsActivity : AppCompatActivity()  {

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_new_applicants)

        // Account
        val addUserName = findViewById<EditText>(R.id.user_name)
        val addPassWord = findViewById<EditText>(R.id.password)

        // Profile
        val addFirstName = findViewById<EditText>(R.id.first_name)
        val addLastName = findViewById<EditText>(R.id.last_name)
        val addLocation = findViewById<EditText>(R.id.location)
        val addJob = findViewById<EditText>(R.id.job)




        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            // Profile
            val firstName = addFirstName.text.toString()
            val lastName = addLastName.text.toString()
            val location = addLocation.text.toString()
            val job = addJob.text.toString()

            // Account
            val userName = addUserName.text.toString()
            val passWord = addPassWord.text.toString()
            val role = "user"
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val profile = Profile(firstName = firstName, lastName = lastName, location = location, job = job)
                applicantsViewModel.insertProfile(profile)
                val account = Account(username = userName, password = passWord, role = role)
                applicantsViewModel.insertAccount(account)
                val intent = Intent(this@NewApplicantsActivity, ApplicantsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
