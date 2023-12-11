package com.capstone.bloomy.data.remote.indonesialocation

import com.capstone.bloomy.data.response.KotaResponse
import com.capstone.bloomy.data.response.ProvinsiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IndonesiaLocationService {

    @GET("provinsi")
    fun getProvinsi() : Call<ProvinsiResponse>

    @GET("kota")
    fun getKota(@Query("id_provinsi") id_provinsi : Int) : Call<KotaResponse>
}