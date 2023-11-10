package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.R

class ProfileDetailsActivity : AppCompatActivity() {

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }


    var addUserName: String? = null
    var addAccountRole: String? = null

    private lateinit var name: TextView
    private lateinit var job: TextView
    private lateinit var location: TextView
    private lateinit var education: TextView
    private lateinit var username: TextView
    private lateinit var userrole: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)

        val role = intent.getStringExtra("role") ?: "guest"

        // Initialize TextViews
        name = findViewById(R.id.name)
        job = findViewById(R.id.job)
        location = findViewById(R.id.location)
        education = findViewById(R.id.education)

        if (role == "admin"){
            username = findViewById(R.id.user_name)
            userrole = findViewById(R.id.role)
            }
        // Retrieve profile ID from intent
        val profileId = intent.getIntExtra("profileId", -1)

        if (role == "admin"){
        applicantsViewModel.getProfileAndAccountById(profileId).observe(this) { accountProfile ->
            addUserName = accountProfile.username
            addAccountRole = accountProfile.role
        }
        }
        // Check if profileId is valid
        if (profileId != -1) {
            // Retrieve profile details from the repository based on the profileId
            val repository = (applicationContext as HireHubApplication).repository
            val profileLiveData = repository.getProfileById(profileId)

            // Observe the LiveData and update TextViews when data changes
            profileLiveData.observe(this) { profile ->
                // Populate TextViews with profile details
               name.text = "${profile.firstName} ${profile.lastName}"
                job.text = "Functie: ${profile.job}"
                location.text = "Functie: ${profile.location}"
                education.text = "Opleidingsniveau: ${profile.education}"

                if (role == "admin"){
                    username.text = "Gebruikersnaam: ${addUserName}"
                    userrole.text = "Rol: ${addAccountRole}"
                }
            }
        } else {
            // Handle invalid profileId (optional)
            Toast.makeText(this, "Invalid Profile ID", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if profileId is invalid
        }
    }
}
