package com.example.sl3verbeterd.ui.applicant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.R

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
        val addEducation = findViewById<EditText>(R.id.education)
        val addJob = findViewById<EditText>(R.id.job)

        val button = findViewById<Button>(R.id.button_save)


        // access the items of the list
        val roles = resources.getStringArray(R.array.roles)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, roles)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {



                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

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
            val role = spinner.selectedItem.toString()
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val profile = Profile(firstName = firstName, lastName = lastName, location = location, job = job, education = education, visibility = true)
                applicantsViewModel.insertProfile(profile)
                val account = Account(username = userName, password = passWord, role = role)
                applicantsViewModel.insertAccount(account)
                val intent = Intent(this@NewApplicantsActivity, ApplicantsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
