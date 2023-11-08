package com.example.sl3verbeterd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sl3verbeterd.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import com.example.sl3verbeterd.ui.theme.ApplicantAndroidTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    private val newWordActivityRequestCode = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)





        val navHome = findViewById<Button>(R.id.nav_home_button)
        navHome.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        val navApplicants = findViewById<Button>(R.id.nav_applicants_button)
        navApplicants.setOnClickListener {
            val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
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

    }

}