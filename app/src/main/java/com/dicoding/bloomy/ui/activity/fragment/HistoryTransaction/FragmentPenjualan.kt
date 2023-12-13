package com.dicoding.bloomy.ui.activity.fragment.HistoryTransaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.bloomy.R

class FragmentPenjualan : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_penjualan, container, false)
    }
}
