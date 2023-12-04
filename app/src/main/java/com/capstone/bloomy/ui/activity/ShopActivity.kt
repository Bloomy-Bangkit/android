package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.ActivityShopBinding
import com.capstone.bloomy.ui.adapter.ProductShopAdapter

class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding

    private val productShopList = ArrayList<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarShop)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerViewProductShop.setHasFixedSize(true)

        productShopList.addAll(getListProductShop())
        showProductShopList()

        binding.btnAddProduct.setOnClickListener {
            val shopAddProductIntent = Intent(this, ShopAddProductActivity::class.java)
            startActivity(shopAddProductIntent)
        }
    }

    private fun getListProductShop(): ArrayList<ProductModel> {
        val dataTitleProductShop = resources.getStringArray(R.array.data_title_product_shop)
        val dataImageUrlProductShop = resources.getStringArray(R.array.data_image_url_product_shop)
        val dataGradeProductShop = resources.getStringArray(R.array.data_grade_product_shop)
        val dataPriceProductShop = resources.getStringArray(R.array.data_price_product_shop)
        val dataRatingProductShop = resources.getStringArray(R.array.data_rating_product_shop)
        val dataSoldProductShop = resources.getStringArray(R.array.data_sold_product_shop)
        val dataLocationProductShop = resources.getStringArray(R.array.data_location_product_shop)
        val dataIsFavoriteProductShop = resources.getStringArray(R.array.data_is_favorite_product_shop)
        val productShopList = ArrayList<ProductModel>()

        val minLength = minOf(dataTitleProductShop.size, dataImageUrlProductShop.size, dataGradeProductShop.size, dataPriceProductShop.size, dataRatingProductShop.size, dataSoldProductShop.size, dataLocationProductShop.size, dataIsFavoriteProductShop.size)

        for (i in 0 until minLength) {
            val productShop = ProductModel(dataTitleProductShop[i], dataImageUrlProductShop[i], dataGradeProductShop[i], dataPriceProductShop[i], dataRatingProductShop[i], dataSoldProductShop[i], dataLocationProductShop[i], dataIsFavoriteProductShop[i])
            productShopList.add(productShop)
        }

        return productShopList
    }

    private fun showProductShopList() {
        binding.recyclerViewProductShop.layoutManager = GridLayoutManager(this, 2)

        val productShopAdapter = ProductShopAdapter(productShopList)

        binding.recyclerViewProductShop.adapter = productShopAdapter

        productShopAdapter.setOnItemClickCallback(object : ProductShopAdapter.OnItemClickCallback {
            override fun onItemClicked(product: ProductModel) {
                val shopProductDetailIntent = Intent(this@ShopActivity, ShopProductDetailActivity::class.java)
                startActivity(shopProductDetailIntent)
            }
        })
    }
}
