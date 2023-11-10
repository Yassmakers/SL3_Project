package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.sl3verbeterd.R
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.ProfileListAdapter

import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels

import androidx.lifecycle.observe
import com.example.sl3verbeterd.MainActivity

import com.google.android.material.floatingactionbutton.FloatingActionButton

class ApplicantsActivity : AppCompatActivity(), ProfileListAdapter.ProfileClickListener {

    private val newActivityRequestCode = 1
    private lateinit var role: String // Declare role variable to store user's role

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicants)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProfileListAdapter(this) // Initialize adapter with the listener
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@ApplicantsActivity, NewApplicantsActivity::class.java)
            startActivity(intent)
        }

        // Retrieve user's role from the intent extras
        val role = intent.getStringExtra("role") ?: "guest"
        val id = intent.getIntExtra("id", 0)
        Log.d("ApplicantsActivity", "Received Role: $role, Received ID: $id")

        val navHome = findViewById<Button>(R.id.nav_home_button)
        navHome.setOnClickListener {
            val intent = Intent(this@ApplicantsActivity, MainActivity::class.java)
            intent.putExtra("role", role) // Pass the user's role to MainActivity
            intent.putExtra("id", id)
            startActivity(intent)
        }

        val navApplicants = findViewById<Button>(R.id.nav_applicants_button)
        navApplicants.setOnClickListener {
            // Implement the behavior for the applicants button based on the user's role
            // Use the 'role' variable here to check user's role
        }

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.

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
        val intent = Intent(this@ApplicantsActivity, ProfileDetailsActivity::class.java)
        intent.putExtra("profileId", id)
        startActivity(intent)
    }

    override fun showProfile(id: Int) {
        applicantsViewModel.showProfile(id)
    }

    override fun onAddProfileClick(profile: Profile) {
        val intent = Intent(this@ApplicantsActivity, NewApplicantsActivity::class.java)
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

        val intent = Intent(this@ApplicantsActivity, UpdateApplicantsActivity::class.java)
        intent.putExtra("id", "${profile.id}")
        intent.putExtra("oldFirstName", profile.firstName)
        intent.putExtra("oldLastName", profile.lastName)
        intent.putExtra("oldLocation", profile.location)
        intent.putExtra("oldJob", profile.job)
        intent.putExtra("oldEducation", profile.education)
        intent.putExtra("visibility", profile.visibility)
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