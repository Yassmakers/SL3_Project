package com.example.sl3verbeterd

import android.content.Intent
import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import com.example.sl3verbeterd.ui.theme.ApplicantAndroidTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


//    private val ApplicantsViewModel: ApplicantsViewModel by viewModels {
//        ApplicantsViewModelFactory((application as HireHubApplication).repository)
//    }

    private val newWordActivityRequestCode = 1


//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, ApplicantsActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

////
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
//        val adapter = ProfileListAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)

//        val dao:HireHubDao = HireHubDatabase.getInstance(this).hireHubDao

//        val viewModelPro by viewModels<ProfileViewModel>(
//            factoryProducer = {
//                object : ViewModelProvider.Factory {
//                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                        return ProfileViewModel(dao) as T
//                    }
//                }
//            }
//        )

//        val viewModelApplicants by viewModels<ApplicantsViewModel>(
//            factoryProducer = {
//                object : ViewModelProvider.Factory {
//                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                        return ApplicantsViewModel(dao) as T
//                    }
//                }
//            }
//        )
//        setContent {
//            ApplicantAndroidTheme {
//                val state by viewModelPro.state.collectAsState()
//                ProfileScreen(state = state, onEvent = viewModelPro::onEvent)
//            }
//        }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_applicant, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

}