package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.capstone.bloomy.R
import com.capstone.bloomy.databinding.ActivityShopAddProductBinding

class ShopAddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopAddProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarShopAddProduct)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnAddProduct.setOnClickListener {
            val shopIntent = Intent(this, ShopActivity::class.java)
            startActivity(shopIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        val grade = resources.getStringArray(R.array.value_grade)
        val arrayAdapter = ArrayAdapter(this, R.layout.item_list_grade, grade)

        binding.etGradeAddProduct.setAdapter(arrayAdapter)
    }
}