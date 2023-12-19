package com.capstone.bloomy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.capstone.bloomy.R
import com.capstone.bloomy.databinding.ActivityTransactionBinding
import com.capstone.bloomy.ui.adapter.SectionPagerTransactionAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TransactionActivity : AppCompatActivity() {

    private lateinit var adapter: SectionPagerTransactionAdapter
    private lateinit var binding: ActivityTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarTransaction)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionPagerTransactionAdapter = SectionPagerTransactionAdapter(this)
        adapter = sectionPagerTransactionAdapter

        val viewPager: ViewPager2 = binding.viewPagerTransaction
        viewPager.adapter = sectionPagerTransactionAdapter

        val tabLayout: TabLayout = binding.tabLayoutTransaction
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.purchases,
            R.string.sales
        )
    }
}