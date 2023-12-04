package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.ActivityMarketSellerDetailBinding
import com.capstone.bloomy.ui.adapter.ProductSellerDetailAdapter

class MarketSellerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketSellerDetailBinding

    private val productSellerDetailList = ArrayList<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketSellerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarSellerDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerViewProductSellerDetail.setHasFixedSize(true)

        productSellerDetailList.addAll(getListProductSellerDetail())
        showProductSellerDetailList()
    }

    private fun getListProductSellerDetail(): ArrayList<ProductModel> {
        val dataTitleProductSellerDetail = resources.getStringArray(R.array.data_title_product_seller_detail)
        val dataImageUrlProductSellerDetail = resources.getStringArray(R.array.data_image_url_product_seller_detail)
        val dataGradeProductSellerDetail = resources.getStringArray(R.array.data_grade_product_seller_detail)
        val dataPriceProductSellerDetail = resources.getStringArray(R.array.data_price_product_seller_detail)
        val dataRatingProductSellerDetail = resources.getStringArray(R.array.data_rating_product_seller_detail)
        val dataSoldProductSellerDetail = resources.getStringArray(R.array.data_sold_product_seller_detail)
        val dataLocationProductSellerDetail = resources.getStringArray(R.array.data_location_product_seller_detail)
        val dataIsFavoriteProductSellerDetail = resources.getStringArray(R.array.data_is_favorite_product_seller_detail)
        val productSellerDetailList = ArrayList<ProductModel>()

        val minLength = minOf(dataTitleProductSellerDetail.size, dataImageUrlProductSellerDetail.size, dataGradeProductSellerDetail.size, dataPriceProductSellerDetail.size, dataRatingProductSellerDetail.size, dataSoldProductSellerDetail.size, dataLocationProductSellerDetail.size, dataIsFavoriteProductSellerDetail.size)

        for (i in 0 until minLength) {
            val productShop = ProductModel(dataTitleProductSellerDetail[i], dataImageUrlProductSellerDetail[i], dataGradeProductSellerDetail[i], dataPriceProductSellerDetail[i], dataRatingProductSellerDetail[i], dataSoldProductSellerDetail[i], dataLocationProductSellerDetail[i], dataIsFavoriteProductSellerDetail[i])
            productSellerDetailList.add(productShop)
        }

        return productSellerDetailList
    }

    private fun showProductSellerDetailList() {
        binding.recyclerViewProductSellerDetail.layoutManager = GridLayoutManager(this, 2)

        val productSellerDetailAdapter = ProductSellerDetailAdapter(productSellerDetailList)

        binding.recyclerViewProductSellerDetail.adapter = productSellerDetailAdapter

        productSellerDetailAdapter.setOnItemClickCallback(object : ProductSellerDetailAdapter.OnItemClickCallback {
            override fun onItemClicked(product: ProductModel) {
                val sellerProductDetailIntent = Intent(this@MarketSellerDetailActivity, MarketProductDetailActivity::class.java)
                startActivity(sellerProductDetailIntent)
            }
        })
    }
}