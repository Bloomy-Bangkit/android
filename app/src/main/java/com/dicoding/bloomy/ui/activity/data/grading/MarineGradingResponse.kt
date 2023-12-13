package com.dicoding.bloomy.ui.activity.data.grading

data class MarineGradingResponse(
    val status: Status
)

data class Status(
    val code: Int,
    val message: String,
    val data: Data
)

data class Data(
    val marineclass: String,
    val grade: String
)
