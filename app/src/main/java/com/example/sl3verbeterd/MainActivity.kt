package com.example.sl3verbeterd
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity


class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHome = findViewById<Button>(R.id.nav_home_button)
        navHome.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
        }



        val navApplicants = findViewById<Button>(R.id.nav_applicants_button)
        navApplicants.setOnClickListener {
            val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        val navProfile = findViewById<Button>(R.id.nav_profile_button)
        navProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfilesActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        val navDashboard = findViewById<Button>(R.id.nav_dashboard_button)
        navDashboard.setOnClickListener {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }
}
//        val navProfile = findViewById<Button>(R.id.nav_profile_button)
//        navProfile.setOnClickListener {
//            val intent = Intent(this@MainActivity, ProfilesActivity::class.java)
//            startActivityForResult(intent, newWordActivityRequestCode)
//        }

//        val navDashboard = findViewById<Button>(R.id.nav_dashboard_button)
//        navDashboard.setOnClickListener {
//            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
//            startActivityForResult(intent, newWordActivityRequestCode)
//        }

