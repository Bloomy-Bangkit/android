package com.dicoding.bloomy.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.bloomy.R
import com.dicoding.bloomy.ui.activity.fragment.GradingFragment
import com.dicoding.bloomy.ui.activity.fragment.HomeFragment
import com.dicoding.bloomy.ui.activity.fragment.PricingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_Grading -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, GradingFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_Market -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_Pricing -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, PricingFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
        bottomNavigation.selectedItemId = R.id.navigation_home
    }
}
