package com.example.sl3verbeterd.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.R

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        // Register
        val navRegister = findViewById<Button>(R.id.register)
        navRegister.setOnClickListener {
            val intent = Intent(this@LandingActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Login
        val navLogin = findViewById<Button>(R.id.login)
        navLogin.setOnClickListener {
            val intent = Intent(this@LandingActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // Guest
        val navGuest = findViewById<Button>(R.id.guest)
        navGuest.setOnClickListener {
            val intent = Intent(this@LandingActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}