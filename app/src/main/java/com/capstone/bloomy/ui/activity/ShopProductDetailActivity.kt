package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.bloomy.databinding.ActivityShopProductDetailBinding

class ShopProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarShopProductDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnEditProductDetail.setOnClickListener {
            val shopEditProductIntent = Intent(this, ShopEditProductActivity::class.java)
            startActivity(shopEditProductIntent)
        }

        binding.btnRemoveProductDetail.setOnClickListener {
            val shopIntent = Intent(this, ShopActivity::class.java)
            startActivity(shopIntent)
        }
    }
}