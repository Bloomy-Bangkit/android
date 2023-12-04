package com.capstone.bloomy.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.ProductModel
import com.capstone.bloomy.databinding.FragmentMarketBinding
import com.capstone.bloomy.ui.activity.MarketProductDetailActivity
import com.capstone.bloomy.ui.adapter.FreshCatchMarketAdapter
import com.capstone.bloomy.ui.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MarketFragment : Fragment() {

    private lateinit var adapter: SectionPagerAdapter

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewFreshCatchMarket = view.findViewById<RecyclerView>(R.id.recycler_view_fresh_catch_market)
        recyclerViewFreshCatchMarket.setHasFixedSize(true)

        val freshCatchMarketList = getListFreshCatchMarket()
        showFreshCatchMarketList(recyclerViewFreshCatchMarket, freshCatchMarketList)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        adapter = sectionPagerAdapter

        val viewPager: ViewPager2 = binding.viewPagerGradeMarket
        viewPager.adapter = sectionPagerAdapter

        val tabLayout: TabLayout = binding.tabLayoutGradeMarket
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun getListFreshCatchMarket(): ArrayList<ProductModel> {
        val dataTitleFreshCatchMarket = resources.getStringArray(R.array.data_title_product_seller_detail)
        val dataImageUrlFreshCatchMarket = resources.getStringArray(R.array.data_image_url_product_seller_detail)
        val dataGradeFreshCatchMarket = resources.getStringArray(R.array.data_grade_product_seller_detail)
        val dataPriceFreshCatchMarket = resources.getStringArray(R.array.data_price_product_seller_detail)
        val dataRatingFreshCatchMarket = resources.getStringArray(R.array.data_rating_product_seller_detail)
        val dataSoldFreshCatchMarket = resources.getStringArray(R.array.data_sold_product_seller_detail)
        val dataLocationFreshCatchMarket = resources.getStringArray(R.array.data_location_product_seller_detail)
        val dataIsFavoriteFreshCatchMarket = resources.getStringArray(R.array.data_is_favorite_product_seller_detail)
        val freshCatchMarketList = ArrayList<ProductModel>()

        val minLength = minOf(dataTitleFreshCatchMarket.size, dataImageUrlFreshCatchMarket.size, dataGradeFreshCatchMarket.size, dataPriceFreshCatchMarket.size, dataRatingFreshCatchMarket.size, dataSoldFreshCatchMarket.size, dataLocationFreshCatchMarket.size, dataIsFavoriteFreshCatchMarket.size)

        for (i in 0 until minLength) {
            val freshCatchMarket = ProductModel(dataTitleFreshCatchMarket[i], dataImageUrlFreshCatchMarket[i], dataGradeFreshCatchMarket[i], dataPriceFreshCatchMarket[i], dataRatingFreshCatchMarket[i], dataSoldFreshCatchMarket[i], dataLocationFreshCatchMarket[i], dataIsFavoriteFreshCatchMarket[i])
            freshCatchMarketList.add(freshCatchMarket)
        }

        return freshCatchMarketList
    }

    private fun showFreshCatchMarketList(recyclerView: RecyclerView, freshCatchMarketList: ArrayList<ProductModel>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val freshCatchMarketAdapter = FreshCatchMarketAdapter(freshCatchMarketList)

        recyclerView.adapter = freshCatchMarketAdapter

        freshCatchMarketAdapter.setOnItemClickCallback(object : FreshCatchMarketAdapter.OnItemClickCallback {
            override fun onItemClicked(freshCatchMarket: ProductModel) {
                val detailFreshCatchMarketIntent = Intent(requireActivity(), MarketProductDetailActivity::class.java)
                startActivity(detailFreshCatchMarketIntent)
            }
        })
    }

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.grade_a,
            R.string.grade_b,
            R.string.grade_c
        )
    }
}