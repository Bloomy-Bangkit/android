package com.dicoding.bloomy.ui.activity.data.product

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductService {

    @GET("/products")
    fun getProducts(@Header("Authorization") token: String): Call<ApiResponse>
}
