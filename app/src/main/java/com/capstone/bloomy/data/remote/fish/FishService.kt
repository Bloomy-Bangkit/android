package com.capstone.bloomy.data.remote.fish

import com.capstone.bloomy.data.response.FishByIdResponse
import com.capstone.bloomy.data.response.FishResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FishService {

    @GET("fishs")
    suspend fun getFish(): Response<FishResponse>

    @GET("fish/{id}")
    suspend fun getFishById(
        @Path("id") id: String
    ): Response<FishByIdResponse>
}