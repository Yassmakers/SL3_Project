package com.example.sl3verbeterd.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.ProfileListAdapter
import com.example.sl3verbeterd.R
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory

class LandingActivity : AppCompatActivity() {

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val id = 3

        applicantsViewModel.getProfileAndAccountById(id).observe(this, Observer { profileAndAccount ->
            profileAndAccount?.let {
                // Display user profile information if available
                val profileName = "${it.firstName} ${it.lastName}"
                val profileJob = "Functie: ${it.job}"
                val profileLocation = "Location: ${it.location}"
                val profileEducation = "Opleidingsniveau: ${it.location}"


            }
        })

        // Register
        val navRegister = findViewById<Button>(R.id.register)
        navRegister.setOnClickListener {
            val intent = Intent(this@LandingActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Login
        val navLogin = findViewById<Button>(R.id.login)
        navLogin.setOnClickListener {
            val intent = Intent(this@LandingActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // Guest
        val navGuest = findViewById<Button>(R.id.guest)
        navGuest.setOnClickListener {
            val intent = Intent(this@LandingActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}