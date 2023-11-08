package com.example.sl3verbeterd.ui.applicant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sl3verbeterd.R
import android.app.Activity
import android.content.Intent

import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.Profile

import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateApplicantsActivity : AppCompatActivity() {

    private val applicantsViewModel: ApplicantsViewModel by viewModels {
        ApplicantsViewModelFactory((applicationContext as HireHubApplication).repository)
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_update_applicants)



        val id = intent.getStringExtra("id")!!.toInt()



        val AddFirstName = findViewById<EditText>(R.id.first_name)
        val AddLastName = findViewById<EditText>(R.id.last_name)


        val oldFirstName = intent.getStringExtra("oldFirstName").toString()
        findViewById<EditText>(R.id.first_name).apply{
            hint = oldFirstName
        }

        val oldLastName = intent.getStringExtra("oldLastName").toString()
        findViewById<EditText>(R.id.last_name).apply{
            hint = oldLastName
        }


        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            val firstName = AddFirstName.text.toString()
            val lastName = AddLastName.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val profile = Profile(firstName = firstName, lastName = lastName, phoneNumber = "PlaceHolder", id = id)

                applicantsViewModel.updateProfile(profile)
                val intent = Intent(this@UpdateApplicantsActivity, ApplicantsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}



