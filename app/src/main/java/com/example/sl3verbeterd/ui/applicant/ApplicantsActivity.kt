package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.sl3verbeterd.R
import android.app.PendingIntent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.ProfileListAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

import android.app.Activity
import android.app.Application
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels

import androidx.lifecycle.observe

import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Activity for entering a word.
 */

class ApplicantsActivity : AppCompatActivity(), ProfileListAdapter.ProfileClickListener {

    private val newWordActivityRequestCode = 1
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
            startActivityForResult(intent, newWordActivityRequestCode)
        }


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        applicantsViewModel.allProfiles.observe(owner = this) { profiles ->
            // Update the cached copy of the words in the adapter.
            profiles.let { adapter.submitList(it) }
        }


    }
        override fun onProfileClick(profile: Profile) {
            // Handle profile click
        }

    override fun onAddProfileClick(profile: Profile) {
        val intent = Intent(this@ApplicantsActivity, NewApplicantsActivity::class.java)
        startActivityForResult(intent, newWordActivityRequestCode)

    }

    override fun onDeleteClick(profile: Profile) {
        // Create a confirmation dialog
        AlertDialog.Builder(this)
            .setTitle("Delete Profile")
            .setMessage("Are you sure you want to delete ${profile.firstName} ${profile.lastName}?")
            .setPositiveButton("Yes") { _, _ ->
                // User confirmed, delete the profile here
                applicantsViewModel.deleteProfile(profile)
            }
            .setNegativeButton("No") { dialog, _ ->
                // User cancelled the delete operation, dismiss the dialog
                dialog.dismiss()
            }
            .show()
    }

    override fun onUpdateProfileClick(profile: Profile) {
        val intent = Intent(this@ApplicantsActivity, UpdateApplicantsActivity::class.java)
        startActivityForResult(intent, newWordActivityRequestCode)
//        applicantsViewModel.updateProfile(profile)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(ApplicantsActivity.EXTRA_REPLY)?.let { reply ->
                val profile = Profile(reply, reply, reply)
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