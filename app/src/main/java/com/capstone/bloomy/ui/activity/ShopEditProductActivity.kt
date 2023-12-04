package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.capstone.bloomy.R
import com.capstone.bloomy.databinding.ActivityShopEditProductBinding

class ShopEditProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopEditProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarShopEditProduct)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnEdit.setOnClickListener {
            val shopProductDetailActivity = Intent(this, ShopProductDetailActivity::class.java)
            startActivity(shopProductDetailActivity)
        }
    }

    override fun onResume() {
        super.onResume()

        val grade = resources.getStringArray(R.array.value_grade)
        val arrayAdapter = ArrayAdapter(this, R.layout.item_list_grade, grade)

        binding.etGradeEditProduct.setAdapter(arrayAdapter)
    }
}