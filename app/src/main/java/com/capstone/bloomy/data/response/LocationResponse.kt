package com.capstone.bloomy.data.response

import com.google.gson.annotations.SerializedName

data class ProvinsiTestResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class KotaTestResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)