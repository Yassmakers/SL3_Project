package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Your BottomNavigationView
        val navView = binding.bottomNavigation

        // Handle item selection listener if needed
        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Handle home item click
                    true
                }
                R.id.navigation_dashboard -> {
                    // Handle dashboard item click
                    true
                }
                R.id.navigation_notifications -> {
                    // Handle notifications item click
                    true
                }
                else -> false
            }
        }
    }
}
