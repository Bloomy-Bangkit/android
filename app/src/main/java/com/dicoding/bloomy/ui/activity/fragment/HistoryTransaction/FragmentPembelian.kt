package com.dicoding.bloomy.ui.activity.fragment.HistoryTransaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.bloomy.R
import com.dicoding.bloomy.ui.activity.data.RetrofitInstance
import com.dicoding.bloomy.ui.activity.data.product.ApiResponse
import com.dicoding.bloomy.ui.activity.data.product.ProductAdapter
import retrofit2.Call

class FragmentPembelian : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_penjualan, container, false)

        recyclerView = view.findViewById(R.id.listitem)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter()

        fetchDataFromApi()

        recyclerView.adapter = adapter

        return view
    }

    private fun fetchDataFromApi() {
        val token = "bearer_token"
        val call = RetrofitInstance.productService.getProducts("Bearer $token")

        call.enqueue(object : retrofit2.Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: retrofit2.Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    data?.let { adapter.setData(it) }
                } else {

                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }
        })
    }
}
