package com.example.sl3verbeterd

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import com.example.sl3verbeterd.ui.applicant.NewApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ProfileDetailsActivity
import com.example.sl3verbeterd.ui.applicant.UpdateApplicantsActivity
import com.example.sl3verbeterd.ui.auth.LandingActivity
import com.example.sl3verbeterd.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity(), ProfileListAdapter.ProfileClickListener {

    private val newActivityRequestCode = 1

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProfileListAdapter(this) // Initialize adapter with the listener
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val navHomeButton = findViewById<Button>(R.id.nav_home_button)
        val navApplicantsButton = findViewById<Button>(R.id.nav_applicants_button)
        val navProfileButton = findViewById<Button>(R.id.nav_profile_button)
        val navLogoutButton = findViewById<Button>(R.id.nav_logout_button)



        // Retrieve the user's role from the session, default to "guest" if not provided
        val role = intent.getStringExtra("role") ?: "guest"
        val username = intent.getStringExtra("username") ?: "Gast"
        val id = intent.getIntExtra("id", 0)

        Log.d("MainActivity", "Received Role: $role, Received ID: $id, Received Name: $username")

        // Hide "Applicants" and "Dboard" buttons if the user has a "guest" role
        if (role == "guest") {
            navProfileButton.visibility = View.GONE
            navApplicantsButton.visibility = View.GONE
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

        if (role == "guest"){
            applicantsViewModel.allVisibleProfiles.observe(owner = this) { profiles ->
                // Update the cached copy of the words in the adapter.
                profiles.let { adapter.submitList(it) }
            }} else {
            applicantsViewModel.allProfiles.observe(owner = this) { profiles ->
                // Update the cached copy of the words in the adapter.
                profiles.let { adapter.submitList(it) }
            }}

    }

    private fun handleNavigation(destination: String, role: String, id: Int) {
        // Check the user's role and navigate accordingly
        when (destination) {
            "home" -> {
                // Implement behavior for the home button
            }
            "applicants" -> {
                // If the user is an admin, navigate to ApplicantsActivity
                if (role == "guest") {
                    val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else {

                    val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
                    intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
                    intent.putExtra("id", id)
                    startActivity(intent)
                    finish() // Finish MainActivity to prevent going back when navigating to ApplicantsActivity
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

    override fun onProfileClick(profile: Profile) {
        // Handle profile click
    }

    override fun onResetProfileClick(profile: Profile) {
        // Handle profile click
    }

    override fun onShowDetailsClick(id: Int) {
        // Implement the behavior for showing profile details based on the provided id.
        // You can navigate to a new activity or fragment to show the details.
        // Example:
        val role = intent.getStringExtra("role") ?: "guest"
        val intent = Intent(this@MainActivity, ProfileDetailsActivity::class.java)
        intent.putExtra("profileId", id)
        intent.putExtra("role", role)
        startActivity(intent)
    }

    override fun showProfile(id: Int) {
        applicantsViewModel.showProfile(id)
    }

    override fun onAddProfileClick(profile: Profile) {
        val intent = Intent(this@MainActivity, NewApplicantsActivity::class.java)
        startActivity(intent)

    }

    override fun onDeleteClick(profile: Profile) {
        // Create a confirmation dialog
        AlertDialog.Builder(this)
            .setTitle("Delete Profile")
            .setMessage("Are you sure you want to delete ${profile.firstName} ${profile.lastName}?")
            .setPositiveButton("Yes") { _, _ ->
                var id = "${profile.id}".toInt()
                // User confirmed, delete the profile here
                applicantsViewModel.deleteProfile(profile)
                applicantsViewModel.deleteAccountToo(id)

            }
            .setNegativeButton("No") { dialog, _ ->
                // User cancelled the delete operation, dismiss the dialog
                dialog.dismiss()
            }
            .show()
    }



    override fun onUpdateProfileClick(profile: Profile) {
        val role = intent.getStringExtra("role") ?: "guest"
        val intent = Intent(this@MainActivity, UpdateApplicantsActivity::class.java)
        intent.putExtra("id", "${profile.id}")
        intent.putExtra("oldFirstName", profile.firstName)
        intent.putExtra("oldLastName", profile.lastName)
        intent.putExtra("oldLocation", profile.location)
        intent.putExtra("oldJob", profile.job)
        intent.putExtra("oldEducation", profile.education)
        intent.putExtra("oldVisibility", profile.visibility.toString())

        intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
        startActivity(intent)
    }

    override fun onToggleVisibilityClick(profile: Profile) {
        val id = profile.id
        applicantsViewModel.toggleVisibility(id)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(ApplicantsActivity.EXTRA_REPLY)?.let { reply ->
                val profile = Profile(reply, reply, reply, reply, reply, true)
                applicantsViewModel.insertProfile(profile)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.sl3verbeterd.REPLY"
    }
}
