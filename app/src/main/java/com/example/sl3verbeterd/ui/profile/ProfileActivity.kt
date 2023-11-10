package com.example.sl3verbeterd.ui.profile
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.R
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.ProfileListAdapter
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import com.example.sl3verbeterd.ui.applicant.NewApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ProfileDetailsActivity
import com.example.sl3verbeterd.ui.applicant.UpdateApplicantsActivity
import com.example.sl3verbeterd.ui.auth.LandingActivity
import com.example.sl3verbeterd.ui.auth.RegisterActivity

class ProfileActivity : AppCompatActivity(), ProfileListAdapter.ProfileClickListener{

    private lateinit var name: TextView
    private lateinit var job: TextView
    private lateinit var location: TextView
    private lateinit var education: TextView

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    var userID = 0
        var visibility = true

    var profile: Profile? = null

        name = findViewById(R.id.name)
        job = findViewById(R.id.job)
        location = findViewById(R.id.location)
        education = findViewById(R.id.education)

    val role = intent.getStringExtra("role") ?: "guest"
    val id = intent.getIntExtra("id", 0)

        val buttonVisibility = findViewById<Button>(R.id.button_visibility)
        val buttonDelete = findViewById<Button>(R.id.button_delete)
        val buttonUpdate = findViewById<Button>(R.id.button_update)
        val buttonReset = findViewById<Button>(R.id.button_reset)


    applicantsViewModel.getProfileAndAccountById(id).observe(this, Observer { profileAndAccount ->
        profileAndAccount?.let {
            // Display user profile information if available
            val profileName = "${it.firstName} ${it.lastName}"
            val profileJob = "Functie: ${it.job}"
            val profileLocation = "Location: ${it.location}"
            val profileEducation = "Opleidingsniveau: ${it.education}"


            userID = it.id
            visibility = it.visibility

            Log.d("ValCheck", "Received ID: ${it.id}, received username ${it.username}, received firstname ${it.firstName}")
            name.text = profileName
            job.text = profileJob
            location.text = profileLocation
            education.text = profileEducation


            // Now, create a new Profile object using the extracted data
            profile = Profile(
                firstName = it.firstName,
                lastName = it.lastName,
                location = it.location,
                job = it.job,
                education = it.education,
                visibility = it.visibility,
                id = it.id
            )


        }
    })



        val usableUserID = userID


        // Set OnClickListener for the delete button
        buttonDelete.setOnClickListener {
            // Call the onDeleteClick method when the delete button is clicked
            onDeleteClick(profile ?: return@setOnClickListener)
        }

        // Set OnClickListener for the delete button
        buttonReset.setOnClickListener {
            // Call the onDeleteClick method when the delete button is clicked
            onResetProfileClick(profile ?: return@setOnClickListener)
        }

        buttonVisibility.setOnClickListener {
            // Call the toggleVisibility method when the visibility button is clicked
                onToggleVisibilityClick(profile ?: return@setOnClickListener)

        }




        buttonUpdate.setOnClickListener {
            // Call the onDeleteClick method when the delete button is clicked
            onUpdateProfileClick(profile ?: return@setOnClickListener)
        }


        val navHomeButton = findViewById<Button>(R.id.nav_home_button)
        val navApplicantsButton = findViewById<Button>(R.id.nav_applicants_button)
        val navProfileButton = findViewById<Button>(R.id.nav_profile_button)




        // Navigation bar logic per role
        if (role == "guest") {
            navProfileButton.visibility = View.GONE
            navApplicantsButton.visibility = View.GONE
        }

        else if (role == "user"){
            navApplicantsButton.visibility = View.GONE
        }

        else {
//
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
            Log.d("ProfileActivity", "Profile Button Clicked")
            // Implement the behavior for the profile button
            handleNavigation("profile", role, id) // Pass the user's role
        }


    }

    override fun onProfileClick(profile: Profile) {
        // Handle profile click
    }

    override fun onShowDetailsClick(id: Int) {
        // Implement the behavior for showing profile details based on the provided id.
        // You can navigate to a new activity or fragment to show the details.
        // Example:
        val intent = Intent(this@ProfileActivity, ProfileDetailsActivity::class.java)
        intent.putExtra("profileId", id)
        startActivity(intent)
    }

    override fun onToggleVisibilityClick(profile: Profile) {
        val id = profile.id
        applicantsViewModel.toggleVisibility(id)

        // Show a message indicating that visibility has been toggled
        val message = if (profile.visibility) {
            "Profiel niet zichtbaar"
        } else {
            "Profiel zichtbaar"
        }

        // Use a Toast to display the message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProfile(id: Int) {
        applicantsViewModel.showProfile(id)
    }

    override fun onAddProfileClick(profile: Profile) {
        val intent = Intent(this@ProfileActivity, NewApplicantsActivity::class.java)
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

                // Clear all the intent data and return the user to the landing page
                val landingIntent = Intent(this@ProfileActivity, LandingActivity::class.java)
                landingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(landingIntent)
                finish()


            }
            .setNegativeButton("No") { dialog, _ ->
                // User cancelled the delete operation, dismiss the dialog
                dialog.dismiss()
            }
            .show()
    }

    override fun onResetProfileClick(profile: Profile) {
        var id = "${profile.id}".toInt()
        applicantsViewModel.resetProfile(id)
    }

    override fun onUpdateProfileClick(profile: Profile) {
        val role = intent.getStringExtra("role") ?: "guest"
        val userID = intent.getIntExtra("id", 0)

        val intent = Intent(this@ProfileActivity, UpdateApplicantsActivity::class.java)
        intent.putExtra("id", "${profile.id}")
        intent.putExtra("oldFirstName", profile.firstName)
        intent.putExtra("oldLastName", profile.lastName)
        intent.putExtra("oldLocation", profile.location)
        intent.putExtra("oldEducation", profile.education)
        intent.putExtra("oldJob", profile.job)
        intent.putExtra("oldRole", role)
        intent.putExtra("userID", userID)
        startActivity(intent)
    }



    private fun handleNavigation(destination: String, role: String, id: Int) {
        // Check the user's role and navigate accordingly
        when (destination) {
            "home" -> {
                val intent = Intent(this@ProfileActivity, MainActivity::class.java)
                intent.putExtra("role", role) // Pass the user's role to ProfileActivity if needed
                intent.putExtra("id", id)
                startActivity(intent)
            }

            "applicants" -> {

                if (role == "guest") {
                    val intent = Intent(this@ProfileActivity, ApplicantsActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else {

                    val intent = Intent(this@ProfileActivity, ApplicantsActivity::class.java)
                    intent.putExtra("role", role) // Pass the user's role to ApplicantsActivity
                    intent.putExtra("id", id)
                    startActivity(intent)
                    finish() // Finish MainActivity to prevent going back when navigating to ApplicantsActivity
                }
            }

            "profile" -> {
                // Navigate to ProfileActivity
                val intent = Intent(this@ProfileActivity, ProfileActivity::class.java)
                intent.putExtra("role", role) // Pass the user's role to ProfileActivity if needed
                intent.putExtra("id", id)
                startActivity(intent)
            }

        }
    }
}
