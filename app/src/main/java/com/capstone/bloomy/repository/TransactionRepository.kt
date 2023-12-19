package com.capstone.bloomy.repository

import com.capstone.bloomy.data.remote.transaction.TransactionService

class TransactionRepository private constructor(private val transactionService: TransactionService) {

    suspend fun getPurchasesTransaction() = transactionService.getPurchasesTransaction()

    suspend fun getSalesTransaction() = transactionService.getSalesTransaction()

    suspend fun buyProduct(idProduct: String, type: String, weight: Int, datePickup: String) = transactionService.buyProduct(idProduct, type, weight, datePickup)

    companion object {
        @Volatile
        private var INSTANCE: TransactionRepository? = null

        fun getInstance(
            transactionService: TransactionService
        ): TransactionRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TransactionRepository(transactionService)
            }.also { INSTANCE = it }
    }
}