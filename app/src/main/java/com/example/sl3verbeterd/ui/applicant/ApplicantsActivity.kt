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
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        val navHome = findViewById<Button>(R.id.nav_home_button)
        navHome.setOnClickListener {
            val intent = Intent(this@ApplicantsActivity, MainActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        val navApplicants = findViewById<Button>(R.id.nav_applicants_button)
        navApplicants.setOnClickListener {
            val intent = Intent(this@ApplicantsActivity, ApplicantsActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }


    }
        override fun onProfileClick(profile: Profile) {
            // Handle profile click
        }

    override fun showProfile(id: Int) {

                applicantsViewModel.showProfile(id)

//        applicantsViewModel.updateProfile(profile)
//        val intent = Intent(this@ApplicantsActivity, UpdateApplicantsActivity::class.java)
//        startActivityForResult(intent, newWordActivityRequestCode)
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

//            fun onUpdateAccountClick(account: Account){
//            // Account
//            intent.putExtra("accountID", "${account.id}")
//            intent.putExtra("oldUsername", account.username)
//            intent.putExtra("oldPassword", account.username)
//        }

    override fun onUpdateProfileClick(profile: Profile) {
        // Profile

        val intent = Intent(this@ApplicantsActivity, UpdateApplicantsActivity::class.java)


        intent.putExtra("id", "${profile.id}")
        intent.putExtra("oldFirstName", profile.firstName)
        intent.putExtra("oldLastName", profile.lastName)
        intent.putExtra("oldLocation", profile.location)
        intent.putExtra("oldJob", profile.job)

        startActivity(intent)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(ApplicantsActivity.EXTRA_REPLY)?.let { reply ->
                val profile = Profile(reply, reply, reply, reply)
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