package com.example.sl3verbeterd.ui.profile

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.R
import com.example.sl3verbeterd.ui.auth.UserProfile
import android.content.Intent
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity

class ProfileActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val navHomeButton = findViewById<Button>(R.id.nav_home_button)
        val navApplicantsButton = findViewById<Button>(R.id.nav_applicants_button)
        val navProfileButton = findViewById<Button>(R.id.nav_profile_button)
        val navDashboardButton = findViewById<Button>(R.id.nav_dashboard_button)
        val profileTextView = findViewById<TextView>(R.id.profile_info_textview)

        // Retrieve user's role from the intent extras
        val role = intent.getStringExtra("role") ?: "guest"

// Retrieve all extras from the intent for debugging purposes
        val extras = intent.extras
        if (extras != null) {
            for (key in extras.keySet()) {
                Log.d("ProfileActivity", "Key: $key, Value: ${extras[key]}")
            }
        }

        // Retrieve user profile information from the intent extras
        val userProfile: UserProfile? = intent.getParcelableExtra("userProfile")
        // Debug log to check if userProfile is received correctly
        Log.d("ProfileActivity", "Received UserProfile: $userProfile")

        // Display user profile information if available
        userProfile?.let {
            val profileInfo = "ID: ${it.id}\nUsername: ${it.username}\nRole: ${it.role}"
            profileTextView.text = profileInfo
        }

        // Hide "Applicants" and "Dboard" buttons if the user has a "guest" role
        if (role == "guest") {
            navApplicantsButton.visibility = android.view.View.GONE
            navDashboardButton.visibility = android.view.View.GONE
        }

        navHomeButton.setOnClickListener {
            // Implement the behavior for the home button based on the user's role
            handleNavigation("home", role) // Pass the user's role
        }

        navApplicantsButton.setOnClickListener {
            // Implement the behavior for the applicants button based on the user's role
            handleNavigation("applicants", role) // Pass the user's role
        }

        navProfileButton.setOnClickListener {
            Log.d("ProfileActivity", "Profile Button Clicked")
            // Implement the behavior for the profile button
            handleNavigation("profile", role) // Pass the user's role
        }

        navDashboardButton.setOnClickListener {
            // Implement behavior for the dashboard button
            handleNavigation("dashboard", role) // Pass the user's role
        }
    }

    private fun handleNavigation(destination: String, role: String) {
        // Check the user's role and navigate accordingly
        when (destination) {
            "home" -> {
                // Implement behavior for the home button
            }

            "applicants" -> {
                // If the user is an admin, navigate to ApplicantsActivity
                if (role == "admin") {
                    val intent = Intent(this@ProfileActivity, ApplicantsActivity::class.java)
                    intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
                    startActivity(intent)
                    finish() // Finish MainActivity to prevent going back when navigating to ApplicantsActivity
                } else {
                    // Handle the case where the user is not authorized to access ApplicantsActivity
                }
            }

            "profile" -> {
                // Navigate to ProfileActivity
                val intent = Intent(this@ProfileActivity, ProfileActivity::class.java)
                intent.putExtra("role", role) // Pass the user's role to ProfileActivity if needed
                startActivity(intent)
            }

            "dashboard" -> {
                // Implement behavior for the dashboard button
            }
        }
    }
}
