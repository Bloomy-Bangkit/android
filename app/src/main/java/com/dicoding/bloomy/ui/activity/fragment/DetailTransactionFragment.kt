package com.dicoding.bloomy.ui.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dicoding.bloomy.R

class DetailTransactionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_detail_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editIcon = view.findViewById<ImageView>(R.id.editIcon)

        editIcon?.setOnClickListener {
            showEditTransactionDialog()
        }
    }

    private fun showEditTransactionDialog() {
        val editTransactionFragment = EditTransactionFragment()
        editTransactionFragment.show(childFragmentManager, editTransactionFragment.tag)
    }
}