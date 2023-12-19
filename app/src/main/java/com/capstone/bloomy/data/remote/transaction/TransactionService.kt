package com.capstone.bloomy.data.remote.transaction

import com.capstone.bloomy.data.response.BuyProductResponse
import com.capstone.bloomy.data.response.PurchasesTransactionResponse
import com.capstone.bloomy.data.response.SalesTransactionResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TransactionService {

    @GET("transactions/me/buyer")
    suspend fun getPurchasesTransaction(): Response<PurchasesTransactionResponse>

    @GET("transactions/me/seller")
    suspend fun getSalesTransaction(): Response<SalesTransactionResponse>

    @FormUrlEncoded
    @POST("transaction")
    suspend fun buyProduct(
        @Field("idProduct") idProduct: String,
        @Field("type") type: String,
        @Field("weight") weight: Int,
        @Field("datePickup") datePickup: String
    ): BuyProductResponse
}