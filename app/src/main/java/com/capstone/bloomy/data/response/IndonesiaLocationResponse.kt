package com.capstone.bloomy.data.response

import com.google.gson.annotations.SerializedName

data class ProvinsiResponse(

    @field:SerializedName("provinsi")
    val provinsi: List<ProvinsiItem>
)

data class ProvinsiItem(

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("id")
    val id: Int
)

data class KotaResponse(

    @field:SerializedName("kota_kabupaten")
    val kotaKabupaten: List<KotaKabupatenItem>
)

data class KotaKabupatenItem(

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("id_provinsi")
    val idProvinsi: String
)