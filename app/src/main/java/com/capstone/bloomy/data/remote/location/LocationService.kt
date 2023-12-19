package com.capstone.bloomy.data.remote.location

import com.capstone.bloomy.data.response.KotaTestResponse
import com.capstone.bloomy.data.response.ProvinsiTestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    @GET("location/provinsi")
    suspend fun getProvinsi(): ProvinsiTestResponse

    @GET("location/provinsi/{nama}/kota")
    suspend fun getKota(
        @Path("nama") nama: String
    ): KotaTestResponse
}