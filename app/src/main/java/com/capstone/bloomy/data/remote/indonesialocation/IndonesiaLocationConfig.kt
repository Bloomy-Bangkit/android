package com.capstone.bloomy.data.remote.indonesialocation

import com.capstone.bloomy.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object IndonesiaLocationConfig {

    val api: IndonesiaLocationService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_INDONESIA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IndonesiaLocationService::class.java)
    }
}