package com.capstone.bloomy.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.capstone.bloomy.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val splashDuration: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, splashDuration)
    }
}