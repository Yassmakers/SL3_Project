package com.example.sl3verbeterd

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve the user's role from the session
        val role = intent.getStringExtra("role") ?: "guest"
        Log.d("MainActivity", "User Role in MainActivity: $role")
        val navHome = findViewById<Button>(R.id.nav_home_button)
        navHome.setOnClickListener {
            // Implement the behavior for the home button based on the user's role
            handleNavigation("home", role) // Pass the user's role
        }

        val navApplicants = findViewById<Button>(R.id.nav_applicants_button)
        navApplicants.setOnClickListener {
            // Implement the behavior for the applicants button based on the user's role
            handleNavigation("applicants", role) // Pass the user's role
        }

        val navProfile = findViewById<Button>(R.id.nav_profile_button)
        navProfile.setOnClickListener {
            // Implement the behavior for the profile button based on the user's role
            handleNavigation("profile", role) // Pass the user's role
        }

        val navDashboard = findViewById<Button>(R.id.nav_dashboard_button)
        navDashboard.setOnClickListener {
            // Implement the behavior for the dashboard button based on the user's role
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
                    val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
                    intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
                    startActivityForResult(intent, newWordActivityRequestCode)
                    finish() // Finish MainActivity to prevent going back when navigating to ApplicantsActivity
                } else {
                    // Handle the case where the user is not authorized to access ApplicantsActivity
                }
            }
            "profile" -> {
                // Implement behavior for the profile button
            }
            "dashboard" -> {
                // Implement behavior for the dashboard button
            }
        }
    }
}
