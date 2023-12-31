package com.capstone.bloomy.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.bloomy.ui.fragment.TransactionFragment

class SectionPagerTransactionAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TransactionFragment()
        fragment.arguments = Bundle().apply {
            putInt(TransactionFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }
}