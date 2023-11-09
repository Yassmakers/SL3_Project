package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sl3verbeterd.R
import android.app.Activity
import android.content.Intent

import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile

import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateApplicantsActivity : AppCompatActivity() {

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_applicants)



        val id = intent.getStringExtra("id")!!.toInt()


//         Account
        val addUserName = findViewById<EditText>(R.id.user_name)
        val addPassWord = findViewById<EditText>(R.id.password)

        // Profile
        val addFirstName = findViewById<EditText>(R.id.first_name)
        val addLastName = findViewById<EditText>(R.id.last_name)
        val addLocation = findViewById<EditText>(R.id.location)
        val addJob = findViewById<EditText>(R.id.job)


        val oldFirstName = intent.getStringExtra("oldFirstName").toString()
        findViewById<EditText>(R.id.first_name).apply{
            hint = oldFirstName
        }

        val oldLastName = intent.getStringExtra("oldLastName").toString()
        findViewById<EditText>(R.id.last_name).apply{
            hint = oldLastName
        }

        val oldLocation = intent.getStringExtra("oldLocation").toString()
        findViewById<EditText>(R.id.location).apply{
            hint =  oldLocation
        }


        val oldJob = intent.getStringExtra("oldJob").toString()
        findViewById<EditText>(R.id.job).apply{
            hint = oldJob
        }




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

            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
                // Display an error message if username or password is empty
                Toast.makeText(this, "Username and Password are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val profile = Profile(firstName = firstName, lastName = lastName, location = location, job = job, id = id)
                val account = Account(username = userName, password = passWord, role = "user", id = id)

                // Update profile and account details
                applicantsViewModel.updateProfile(profile)
                applicantsViewModel.updateAccount(account)

                // Display success message
                Toast.makeText(this, "Profile and Account details updated successfully", Toast.LENGTH_SHORT).show()

                // Close the current activity
                finish()
            } else {
                // Display an error message if first name or last name is empty
                Toast.makeText(this, "First Name and Last Name are required", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



