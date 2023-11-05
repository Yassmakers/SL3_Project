package com.example.sl3verbeterd

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sl3verbeterd.databinding.ActivityMainBinding
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.sl3verbeterd.ui.theme.ApplicantAndroidTheme


class MainActivity : AppCompatActivity() {

//    private val db by lazy {
//        Room.databaseBuilder(
//            applicationContext,
//            HireHubDatabase::class.java,
//            "HireHub.db"
//        ).build()
//    }





//    private val viewModelAcc by viewModels<AccountViewModel>(
//        factoryProducer = {
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    return AccountViewModel(db.daoAcc) as T
//                }
//            }
//        }
//    )


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dao:HireHubDao = HireHubDatabase.getInstance(this).hireHubDao


//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
//        val adapter = ProfileListAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModelPro by viewModels<ProfileViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return ProfileViewModel(dao) as T
                    }
                }
            }
        )
        setContent {
            ApplicantAndroidTheme {
                val state by viewModelPro.state.collectAsState()
                ProfileScreen(state = state, onEvent = viewModelPro::onEvent)
            }
        }
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