package com.dicoding.bloomy.ui.activity.data.grading

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface GradingApi {

    @Multipart
    @POST("marine/predict")
    fun predictImage(
        @Header("Authorization") authorization: String,
        @Part image: MultipartBody.Part
    ): Call<MarineGradingResponse>

    companion object {
        private const val BASE_URL = "https://api-service-vixypb3qiq-uc.a.run.app"

        fun create(): GradingApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(GradingApi::class.java)
        }
    }
}
