package com.weatherveil.hidden

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.weatherveil.R
import com.weatherveil.utils.AuthManager

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvError: TextView
    private lateinit var progressBar: ProgressBar

    // Panic: press back 3 times quickly to close hidden section
    private var backPressCount = 0
    private var lastBackPress = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prevent screenshots in hidden section
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContentView(R.layout.activity_login)

        // Check if already logged in → go straight to chat
        if (AuthManager.isLoggedIn(this)) {
            goToChat()
            return
        }

        initViews()
    }

    private fun initViews() {
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvError = findViewById(R.id.tvLoginError)
        progressBar = findViewById(R.id.loginProgress)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            attemptLogin(username, password)
        }
    }

    private fun attemptLogin(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both fields.")
            return
        }

        progressBar.visibility = View.VISIBLE
        btnLogin.isEnabled = false

        val user = AuthManager.authenticate(username, password)
        if (user != null) {
            AuthManager.saveSession(this, user.username)
            goToChat()
        } else {
            progressBar.visibility = View.GONE
            btnLogin.isEnabled = true
            showError("Invalid credentials.")
            etPassword.setText("")
        }
    }

    private fun showError(msg: String) {
        tvError.visibility = View.VISIBLE
        tvError.text = msg
    }

    private fun goToChat() {
        val intent = Intent(this, ChatActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    // Panic button: back × 3 in 2 seconds = instant exit
    override fun onBackPressed() {
        val now = System.currentTimeMillis()
        if (now - lastBackPress < 2000) {
            backPressCount++
            if (backPressCount >= 3) {
                finishAffinity() // Close everything
                return
            }
        } else {
            backPressCount = 1
        }
        lastBackPress = now
        super.onBackPressed()
    }
}
