package com.capstone.bloomy.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.capstone.bloomy.R
import com.capstone.bloomy.databinding.ActivityMarketProductDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class MarketProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketProductDetailBinding

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarMarketProductDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tvNameSellerMarketProductDetail.setOnClickListener {
            val sellerDetailIntent = Intent(this, MarketSellerDetailActivity::class.java)
            startActivity(sellerDetailIntent)
        }

        binding.btnBuy.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.buy_bottom_sheet_dialog, null)

            val btnCancel = view.findViewById<ImageView>(R.id.img_cancel_buy_bottom_sheet_dialog)
            val btnBuy = view.findViewById<MaterialButton>(R.id.btn_buy_bottom_sheet_dialog)
            val deliveryMethod = resources.getStringArray(R.array.value_delivery_method)
            val spinner = view.findViewById<Spinner>(R.id.spinner_delivery_method_buy_bottom_sheet_dialog)

            if (spinner != null) {
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, deliveryMethod)

                spinner.adapter = adapter
            }

            btnCancel.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            btnBuy.setOnClickListener {
                bottomSheetDialog.dismiss()

            }

            bottomSheetDialog.setCancelable(false)

            bottomSheetDialog.setContentView(view)

            bottomSheetDialog.show()
        }
    }
}