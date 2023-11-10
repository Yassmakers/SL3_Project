package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.R

class ProfileDetailsActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var job: TextView
    private lateinit var location: TextView
    private lateinit var education: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)

        // Initialize TextViews
        name = findViewById(R.id.name)
        job = findViewById(R.id.job)
        location = findViewById(R.id.location)
        education = findViewById(R.id.education)


        // Retrieve profile ID from intent
        val profileId = intent.getIntExtra("profileId", -1)

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
                // Add similar lines for other profile details like location, job, etc.
            }
        } else {
            // Handle invalid profileId (optional)
            Toast.makeText(this, "Invalid Profile ID", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if profileId is invalid
        }
    }
}
