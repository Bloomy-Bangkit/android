package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import com.capstone.bloomy.R
import com.capstone.bloomy.databinding.ActivityTransactionDetailBinding

class TransactionDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarTransactionDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToTransactionActivity()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateToTransactionActivity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToTransactionActivity() {
        val intent = Intent(this, TransactionActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}