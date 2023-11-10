package com.example.sl3verbeterd

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity
import com.example.sl3verbeterd.ui.auth.LandingActivity
import com.example.sl3verbeterd.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHomeButton = findViewById<Button>(R.id.nav_home_button)
        val navApplicantsButton = findViewById<Button>(R.id.nav_applicants_button)
        val navProfileButton = findViewById<Button>(R.id.nav_profile_button)
        val navDashboardButton = findViewById<Button>(R.id.nav_dashboard_button)
        val navLogoutButton = findViewById<Button>(R.id.nav_logout_button)

        // Retrieve the user's role from the session, default to "guest" if not provided
        val role = intent.getStringExtra("role") ?: "guest"
        val username = intent.getStringExtra("username") ?: "Gast"
        val id = intent.getIntExtra("id", 0)

        Log.d("MainActivity", "Received Role: $role, Received ID: $id, Received Name: $username")

        // Hide "Applicants" and "Dboard" buttons if the user has a "guest" role
        if (role == "guest") {
            navDashboardButton.visibility = View.GONE
        } else {
            // Show logout button only for logged-in users
            navLogoutButton.visibility = View.VISIBLE
            navLogoutButton.setOnClickListener {
                // Implement logout functionality here (e.g., clear user session, log out the user)

                // Redirect the user to the landing activity
                val intent = Intent(this@MainActivity, LandingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }

        navHomeButton.setOnClickListener {
            // Implement the behavior for the home button based on the user's role
            handleNavigation("home", role, id) // Pass the user's role
        }

        navApplicantsButton.setOnClickListener {
            // Implement the behavior for the applicants button based on the user's role
            handleNavigation("applicants", role, id) // Pass the user's role
        }

        navProfileButton.setOnClickListener {
            Log.d("MainActivity", "Profile Button Clicked")
            // Implement the behavior for the profile button
            handleNavigation("profile", role, id) // Pass the user's role
        }


        navDashboardButton.setOnClickListener {
            // Implement the behavior for the dashboard button
            handleNavigation("dashboard", role, id) // Pass the user's role
        }
    }

    private fun handleNavigation(destination: String, role: String, id: Int) {
        // Check the user's role and navigate accordingly
        when (destination) {
            "home" -> {
                // Implement behavior for the home button
            }
            "applicants" -> {
                // If the user is an admin, navigate to ApplicantsActivity
                if (role == "admin") {
                    val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
                    intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
                    intent.putExtra("id", id)
                    startActivity(intent)
                    finish() // Finish MainActivity to prevent going back when navigating to ApplicantsActivity
                }

                else {
                    val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            "profile" -> {
                // Navigate to ProfileActivity
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                intent.putExtra("role", role) // Pass the user's role to ProfileActivity if needed
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
            "dashboard" -> {
                // Implement behavior for the dashboard button
            }
        }
    }
}
