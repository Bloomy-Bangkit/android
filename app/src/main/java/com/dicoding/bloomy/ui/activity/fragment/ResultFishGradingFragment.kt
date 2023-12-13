package com.dicoding.bloomy.ui.activity.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dicoding.bloomy.R
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton

class ResultFishGradingFragment : Fragment() {

    private var isBitmapRecycled = false
    private var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.result_fish_grading, container, false)
        val bundle = arguments
        if (bundle != null) {
            bitmap = bundle.getParcelable("uploadedImage")
            val uploadedImage: ImageView = view.findViewById(R.id.uploadedImageb)
            uploadedImage.setImageBitmap(bitmap)
            isBitmapRecycled = false
        }

        val moreButton: MaterialButton = view.findViewById(R.id.morebuttongrading)
        moreButton.setOnClickListener {
            redirectToGradingFragment()
        }
        return view
    }

    private fun redirectToGradingFragment() {
        if (!isBitmapRecycled) {
            if (bitmap != null && !bitmap!!.isRecycled) {
                bitmap!!.recycle()
                isBitmapRecycled = true
            }
        }

        val gradingFragment = GradingFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.container, gradingFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
