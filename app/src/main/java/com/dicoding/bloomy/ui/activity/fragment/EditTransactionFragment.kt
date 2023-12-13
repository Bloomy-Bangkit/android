package com.dicoding.bloomy.ui.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.bloomy.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditTransactionFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail_transaction, container, false)
    }
}
