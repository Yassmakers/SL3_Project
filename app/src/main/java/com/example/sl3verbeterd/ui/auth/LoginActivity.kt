package com.example.sl3verbeterd.ui.auth
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.sl3verbeterd.HireHubApplication
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubDatabase
import com.example.sl3verbeterd.MainActivity
import com.example.sl3verbeterd.R
import kotlinx.coroutines.CoroutineScope
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import com.example.sl3verbeterd.HireHubRepository
import kotlinx.coroutines.SupervisorJob
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.sl3verbeterd.ui.applicant.ApplicantsActivity

class LoginActivity : AppCompatActivity() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private lateinit var hireHubDao: HireHubDao
    private lateinit var viewModel: ApplicantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hireHubDao = HireHubDatabase.getDatabase(this, applicationScope).hireHubDao()
        val repository = HireHubRepository(hireHubDao)
        viewModel = ViewModelProvider(this, ApplicantsViewModelFactory(repository)).get(ApplicantsViewModel::class.java)

        val loginButton = findViewById<Button>(R.id.login_button)
        val editTextLoginUser = findViewById<EditText>(R.id.editTextLoginUser)
        val editTextLoginPassword = findViewById<EditText>(R.id.editTextLoginPassword)

        loginButton.setOnClickListener {
            val username = editTextLoginUser.text.toString()
            val password = editTextLoginPassword.text.toString()

            viewModel.loginUser(username, password)
        }

        viewModel.loginSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                showLoginSuccessDialog()
                // state id = accountid
                // state role = guest
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                showLoginFailureDialog()
                val intent = Intent(this@LoginActivity, ApplicantsActivity::class.java)
                startActivity(intent)
                // Handle failed login, show an error message, etc.
            }
        })
    }

    private fun showLoginSuccessDialog() {
        val dialogFragment = CustomDialogFragment()
        dialogFragment.show(supportFragmentManager, "CustomDialogFragment")
    }


    private fun showLoginFailureDialog() {
        val builder = AlertDialog.Builder(this)
        val dialog = builder.setTitle("Login Failed")
            .setMessage("Incorrect credentials. Please try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()

        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()
        }, 3000) // 3000 milliseconds (3 seconds) delay before dismissing the dialog
    }
}

