package com.dicoding.bloomy.ui.activity.data.prediction

data class PricePredictionRequest(
    val Grade: Float,
    val catchingMethod: Float,
    val sustainability: Float,
    val acctualPrice: Float
)

data class PricePredictionResponse(
    val Status: Status,
    val data: Data
)

data class Status(
    val code: Int,
    val message: String
)

data class Data(
    val price: Int
)
