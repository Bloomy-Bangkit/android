package com.capstone.bloomy.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.FragmentMarketGradeProductBinding
import com.capstone.bloomy.ui.activity.MarketProductDetailActivity
import com.capstone.bloomy.ui.adapter.MarketGradeProductAdapter

class MarketGradeProductFragment : Fragment() {

    private var _binding: FragmentMarketGradeProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketGradeProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        val recyclerViewMarketGradeProduct = view.findViewById<RecyclerView>(R.id.recycler_view_market_grade_product)
        recyclerViewMarketGradeProduct.setHasFixedSize(true)

        val marketGradeProductList: ArrayList<ProductModel> = when (position) {
            1 -> {
                getListMarketGradeProductA()
            }
            2 -> {
                getListMarketGradeProductB()
            }
            else -> {
                getListMarketGradeProductC()
            }
        }

        showMarketGradeProductList(recyclerViewMarketGradeProduct, marketGradeProductList)
    }

    private fun getListMarketGradeProductA(): ArrayList<ProductModel> {
        val dataTitleMarketGradeProduct = resources.getStringArray(R.array.data_title_product_grade_a)
        val dataImageUrlMarketGradeProduct = resources.getStringArray(R.array.data_image_url_product_grade_a)
        val dataGradeMarketGradeProduct = resources.getStringArray(R.array.data_grade_product_grade_a)
        val dataPriceMarketGradeProduct = resources.getStringArray(R.array.data_price_product_grade_a)
        val dataRatingMarketGradeProduct = resources.getStringArray(R.array.data_rating_product_grade_a)
        val dataSoldMarketGradeProduct = resources.getStringArray(R.array.data_sold_product_grade_a)
        val dataLocationMarketGradeProduct = resources.getStringArray(R.array.data_location_product_grade_a)
        val dataIsFavoriteMarketGradeProduct = resources.getStringArray(R.array.data_is_favorite_product_grade_a)
        val marketGradeProductList = ArrayList<ProductModel>()

        val minLength = minOf(dataTitleMarketGradeProduct.size, dataImageUrlMarketGradeProduct.size, dataGradeMarketGradeProduct.size, dataPriceMarketGradeProduct.size, dataRatingMarketGradeProduct.size, dataSoldMarketGradeProduct.size, dataLocationMarketGradeProduct.size, dataIsFavoriteMarketGradeProduct.size)

        for (i in 0 until minLength) {
            val marketGradeProduct = ProductModel(dataTitleMarketGradeProduct[i], dataImageUrlMarketGradeProduct[i], dataGradeMarketGradeProduct[i], dataPriceMarketGradeProduct[i], dataRatingMarketGradeProduct[i], dataSoldMarketGradeProduct[i], dataLocationMarketGradeProduct[i], dataIsFavoriteMarketGradeProduct[i])
            marketGradeProductList.add(marketGradeProduct)
        }

        return marketGradeProductList
    }

    private fun getListMarketGradeProductB(): ArrayList<ProductModel> {
        val dataTitleMarketGradeProduct = resources.getStringArray(R.array.data_title_product_grade_b)
        val dataImageUrlMarketGradeProduct = resources.getStringArray(R.array.data_image_url_product_grade_b)
        val dataGradeMarketGradeProduct = resources.getStringArray(R.array.data_grade_product_grade_b)
        val dataPriceMarketGradeProduct = resources.getStringArray(R.array.data_price_product_grade_b)
        val dataRatingMarketGradeProduct = resources.getStringArray(R.array.data_rating_product_grade_b)
        val dataSoldMarketGradeProduct = resources.getStringArray(R.array.data_sold_product_grade_b)
        val dataLocationMarketGradeProduct = resources.getStringArray(R.array.data_location_product_grade_b)
        val dataIsFavoriteMarketGradeProduct = resources.getStringArray(R.array.data_is_favorite_product_grade_b)
        val marketGradeProductList = ArrayList<ProductModel>()

        val minLength = minOf(dataTitleMarketGradeProduct.size, dataImageUrlMarketGradeProduct.size, dataGradeMarketGradeProduct.size, dataPriceMarketGradeProduct.size, dataRatingMarketGradeProduct.size, dataSoldMarketGradeProduct.size, dataLocationMarketGradeProduct.size, dataIsFavoriteMarketGradeProduct.size)

        for (i in 0 until minLength) {
            val marketGradeProduct = ProductModel(dataTitleMarketGradeProduct[i], dataImageUrlMarketGradeProduct[i], dataGradeMarketGradeProduct[i], dataPriceMarketGradeProduct[i], dataRatingMarketGradeProduct[i], dataSoldMarketGradeProduct[i], dataLocationMarketGradeProduct[i], dataIsFavoriteMarketGradeProduct[i])
            marketGradeProductList.add(marketGradeProduct)
        }

        return marketGradeProductList
    }

    private fun getListMarketGradeProductC(): ArrayList<ProductModel> {
        val dataTitleMarketGradeProduct = resources.getStringArray(R.array.data_title_product_grade_c)
        val dataImageUrlMarketGradeProduct = resources.getStringArray(R.array.data_image_url_product_grade_c)
        val dataGradeMarketGradeProduct = resources.getStringArray(R.array.data_grade_product_grade_c)
        val dataPriceMarketGradeProduct = resources.getStringArray(R.array.data_price_product_grade_c)
        val dataRatingMarketGradeProduct = resources.getStringArray(R.array.data_rating_product_grade_c)
        val dataSoldMarketGradeProduct = resources.getStringArray(R.array.data_sold_product_grade_c)
        val dataLocationMarketGradeProduct = resources.getStringArray(R.array.data_location_product_grade_c)
        val dataIsFavoriteMarketGradeProduct = resources.getStringArray(R.array.data_is_favorite_product_grade_c)
        val marketGradeProductList = ArrayList<ProductModel>()

        val minLength = minOf(dataTitleMarketGradeProduct.size, dataImageUrlMarketGradeProduct.size, dataGradeMarketGradeProduct.size, dataPriceMarketGradeProduct.size, dataRatingMarketGradeProduct.size, dataSoldMarketGradeProduct.size, dataLocationMarketGradeProduct.size, dataIsFavoriteMarketGradeProduct.size)

        for (i in 0 until minLength) {
            val marketGradeProduct = ProductModel(dataTitleMarketGradeProduct[i], dataImageUrlMarketGradeProduct[i], dataGradeMarketGradeProduct[i], dataPriceMarketGradeProduct[i], dataRatingMarketGradeProduct[i], dataSoldMarketGradeProduct[i], dataLocationMarketGradeProduct[i], dataIsFavoriteMarketGradeProduct[i])
            marketGradeProductList.add(marketGradeProduct)
        }

        return marketGradeProductList
    }

    private fun showMarketGradeProductList(recyclerView: RecyclerView, marketGradeProductList: ArrayList<ProductModel>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val marketGradeProductAdapter = MarketGradeProductAdapter(marketGradeProductList)

        recyclerView.adapter = marketGradeProductAdapter

        marketGradeProductAdapter.setOnItemClickCallback(object : MarketGradeProductAdapter.OnItemClickCallback {
            override fun onItemClicked(marketGradeProduct: ProductModel) {
                val detailMarketGradeProductIntent = Intent(requireActivity(), MarketProductDetailActivity::class.java)
                startActivity(detailMarketGradeProductIntent)
            }
        })
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}