package com.dicoding.bloomy.ui.activity.data.prediction

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PricingService {
    @POST("/price/predict")
    fun predictPrice(@Body request: PricePredictionRequest): Call<PricePredictionResponse>
}
