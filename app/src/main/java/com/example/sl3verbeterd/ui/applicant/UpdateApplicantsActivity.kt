package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.sl3verbeterd.R

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels

import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile

class UpdateApplicantsActivity : AppCompatActivity() {

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_applicants)



        val id = intent.getStringExtra("id")!!.toInt()
        val role = intent.getStringExtra("role") ?: "guest"
        var oldRole: String? = null
        val userID = intent.getIntExtra("userID", 0)




//         Account

        Log.d("ApplicantsActivity", "Received Role: $role")
        // Profile
        val addFirstName = findViewById<EditText>(R.id.first_name)
        val addLastName = findViewById<EditText>(R.id.last_name)
        val addLocation = findViewById<EditText>(R.id.location)
        val addJob = findViewById<EditText>(R.id.job)
        val addEducation = findViewById<EditText>(R.id.education)

        val addUserName = findViewById<EditText>(R.id.user_name)
        val addPassWord = findViewById<EditText>(R.id.password)
        addUserName.visibility = if (role == "admin" || userID == id) View.VISIBLE else View.INVISIBLE
        addPassWord.visibility = if (role == "admin" || userID == id) View.VISIBLE else View.INVISIBLE


        applicantsViewModel.getProfileAndAccountById(id).observe(this) { accountProfile ->
            val oldUserName = accountProfile.username
            val oldPassWord = accountProfile.password
            oldRole = accountProfile.role

            addUserName.setText(oldUserName)
            addPassWord.setText(oldPassWord)
        }

        // access the items of the list
        val roles = resources.getStringArray(R.array.roles)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.visibility = if (role == "admin") View.VISIBLE else View.INVISIBLE
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

        val oldEducation = intent.getStringExtra("oldEducation").toString()
        findViewById<EditText>(R.id.education).apply{
            hint =  oldEducation
        }


        val oldJob = intent.getStringExtra("oldJob").toString()
        findViewById<EditText>(R.id.job).apply{
            hint = oldJob
        }


        val visibility = intent.getStringExtra("oldVisibility").toBoolean()




        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {

            //Account
            val userName = addUserName.text.toString()
            val passWord = addPassWord.text.toString()

            // Profile
            val firstName = addFirstName.text.toString()
            val lastName = addLastName.text.toString()
            val location = addLocation.text.toString()
            val job = addJob.text.toString()
            val education = addEducation.text.toString()
            val rolePick = spinner.selectedItem.toString()






            val account: Account

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && location.isNotEmpty()&& job.isNotEmpty()&& education.isNotEmpty()) {


                account = if (role == "admin"){
                    Account(username = userName, password = passWord, role = rolePick, id = id)
                } else {
                    Account(username = userName, password = passWord, role = oldRole ?: "", id = id)
                }

                val profile = Profile(firstName = firstName, lastName = lastName, location = location, job = job, education = education, id = id, visibility = visibility)





                // Update profile and account details
                applicantsViewModel.updateProfile(profile)
                applicantsViewModel.updateAccount(account)



                // Display success message
                Toast.makeText(this, "Details updated successfully", Toast.LENGTH_SHORT).show()

                // Close the current activity
                finish()
            } else {
                // Display an error message if first name or last name is empty
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



