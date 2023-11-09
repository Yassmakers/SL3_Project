package com.example.sl3verbeterd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.R
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val firstNameTextView = findViewById<TextView>(R.id.textViewFirstName)
        val lastNameTextView = findViewById<TextView>(R.id.textViewLastName)
        val locationTextView = findViewById<TextView>(R.id.textViewLocation)
        val jobTextView = findViewById<TextView>(R.id.textViewJob)

        // Inside ProfileActivity
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val location = intent.getStringExtra("location")
        val job = intent.getStringExtra("job")

        // Check for null values before setting text to TextViews
        firstName?.let { firstNameTextView.text = it }
        lastName?.let { lastNameTextView.text = it }
        location?.let { locationTextView.text = it }
        job?.let { jobTextView.text = it }

        val role = intent.getStringExtra("role")

        // Example usage of handleNavigation function
        handleNavigation("applicants", role)
    }

    private fun handleNavigation(destination: String, role: String?) {
        // Check the user's role and navigate accordingly
        when (destination) {
            "applicants" -> {
                // If the user is an admin, navigate to ApplicantsActivity
                if (role == "admin") {
                    val intent = Intent(this@ProfileActivity, ApplicantsActivity::class.java)
                    intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
                    startActivity(intent)
                    finish() // Finish ProfileActivity to prevent going back when navigating to ApplicantsActivity
                } else {
                    // Handle the case where the user is not authorized to access ApplicantsActivity
                }
            }
            // Handle other destinations as needed
        }
    }
}
