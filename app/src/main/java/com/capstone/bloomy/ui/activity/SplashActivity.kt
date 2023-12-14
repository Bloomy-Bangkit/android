package com.capstone.bloomy.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.bloomy.R
import com.capstone.bloomy.data.preferences.UserPreferences
import com.capstone.bloomy.data.preferences.dataStore
import com.capstone.bloomy.ui.viewmodel.UserPreferencesViewModel
import com.capstone.bloomy.ui.viewmodelfactory.UserPreferencesViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val splashDuration: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userPreferences: UserPreferences = UserPreferences.getInstance(application.dataStore)
        val userPreferencesViewModelFactory: UserPreferencesViewModelFactory = UserPreferencesViewModelFactory.getInstance(userPreferences)
        val userPreferencesViewModel: UserPreferencesViewModel by viewModels { userPreferencesViewModelFactory }

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            val homeIntent = Intent(this, HomeActivity::class.java)

            userPreferencesViewModel.getSession().observe(this) { session ->
                if (session != null) {
                    Toast.makeText(this, getString(R.string.welcome_to_bloomy), Toast.LENGTH_SHORT).show()
                    startActivity(mainIntent)
                    finish()
                } else {
                    startActivity(homeIntent)
                    finish()
                }
            }
        }, splashDuration)
    }
}