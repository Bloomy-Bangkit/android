package com.capstone.bloomy.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.capstone.bloomy.R
import com.capstone.bloomy.data.response.ProductByIdData
import com.capstone.bloomy.data.response.ProfileByUsernameData
import com.capstone.bloomy.databinding.ActivityMarketProductDetailBinding
import com.capstone.bloomy.ui.viewmodel.ProductViewModel
import com.capstone.bloomy.ui.viewmodel.ProfileViewModel
import com.capstone.bloomy.ui.viewmodelfactory.ProductViewModelFactory
import com.capstone.bloomy.ui.viewmodelfactory.ProfileViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class MarketProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketProductDetailBinding

    private val productId = PRODUCT_ID
    private val sellerUsername = SELLER_USERNAME

    private val productViewModelFactory: ProductViewModelFactory = ProductViewModelFactory.getInstance(this@MarketProductDetailActivity)
    private val productViewModel: ProductViewModel by viewModels { productViewModelFactory }

    private val profileViewModelFactory: ProfileViewModelFactory = ProfileViewModelFactory.getInstance(this@MarketProductDetailActivity)
    private val profileViewModel: ProfileViewModel by viewModels { profileViewModelFactory }

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarMarketProductDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        productViewModel.getProductById(productId)
        productViewModel.detailProduct.observe(this) { detailProduct ->
            setDetailProduct(detailProduct)
        }

        profileViewModel.getProfileByUsername(sellerUsername)
        profileViewModel.profileByUsername.observe(this) { profile ->
            setSellerProfile(profile)
        }

        binding.tvNameSellerMarketProductDetail.setOnClickListener {
            val sellerDetailIntent = Intent(this, MarketSellerDetailActivity::class.java)
            sellerDetailIntent.putExtra("seller_username", sellerUsername)
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

    private fun setDetailProduct(detailProduct: ProductByIdData) {
        with(binding) {
            Glide.with(this@MarketProductDetailActivity)
                .load(detailProduct.picture)
                .into(imgMarketProductDetail)

            tvTitleMarketProductDetail.text = detailProduct.nama
            tvGradeMarketProductDetail.text = formatGrade(detailProduct.grade)
            tvPriceMarketProductDetail.text = formatCurrency(detailProduct.price)
            tvQuantityMarketProductDetail.text = formatWeight(detailProduct.weight)
            tvValueDescriptionMarketProductDetail.text = detailProduct.description
        }
    }

    private fun setSellerProfile(profile: ProfileByUsernameData) {
        with(binding) {
            Glide.with(this@MarketProductDetailActivity)
                .load(profile.photo)
                .into(imgSellerMarketProductDetail)

            tvNameSellerMarketProductDetail.text = profile.nama.ifEmpty { getString(R.string.default_name) }
            tvLocationSellerMarketProductDetail.text = profile.kota.ifEmpty { getString(R.string.default_location) }
        }
    }

    private fun formatGrade(grade: String): String {
        return "Grade \"$grade\""
    }

    private fun formatCurrency(amount: Int): String {
        val formattedAmount = String.format("Rp%,d", amount)
        return formattedAmount.replace(',', '.')
    }

    private fun formatWeight(weight: Int): String {
        return "$weight kg left"
    }

    companion object {
        var PRODUCT_ID = "product_id"
        var SELLER_USERNAME = "seller_username"
    }
}