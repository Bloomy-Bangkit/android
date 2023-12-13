package com.dicoding.bloomy.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.dicoding.bloomy.R
import com.dicoding.bloomy.ui.activity.fragment.HistoryTransaction.FragmentPembelian
import com.dicoding.bloomy.ui.activity.fragment.HistoryTransaction.FragmentPenjualan

class HistoryFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_history_transaction, container, false)

        viewPager = view.findViewById(R.id.itemlist)
        tabLayout = view.findViewById(R.id.penpem)

        setupViewPager()

        return view
    }

    private fun setupViewPager() {
        val pagerAdapter = HistoryPagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Pembelian"
                1 -> tab.text = "Penjualan"
            }
        }.attach()
    }
}

class HistoryPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentPenjualan()
            1 -> FragmentPembelian()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}

