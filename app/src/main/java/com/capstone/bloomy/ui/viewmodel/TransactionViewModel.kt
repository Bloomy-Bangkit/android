package com.capstone.bloomy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.bloomy.data.response.BuyProductResponse
import com.capstone.bloomy.data.response.PurchasesTransactionData
import com.capstone.bloomy.data.response.SalesTransactionData
import com.capstone.bloomy.repository.TransactionRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TransactionViewModel(private val transactionRepository: TransactionRepository) : ViewModel() {

    private val _purchasesTransaction = MutableLiveData<List<PurchasesTransactionData>>()
    val purchasesTransaction: LiveData<List<PurchasesTransactionData>> = _purchasesTransaction

    private val _salesTransaction = MutableLiveData<List<SalesTransactionData>>()
    val salesTransaction: LiveData<List<SalesTransactionData>> = _salesTransaction

    private val _buyProductResponse = MutableLiveData<BuyProductResponse?>()
    val buyProductResponse: LiveData<BuyProductResponse?> = _buyProductResponse

    private val _responseCode = MutableLiveData<Int>()

    fun getPurchasesTransaction() {
        viewModelScope.launch {
            try {
                val response = transactionRepository.getPurchasesTransaction()

                if (response.isSuccessful) _purchasesTransaction.value = response.body()?.purchasesTransactionData else _responseCode.value = response.code()
            } catch (e: HttpException) {
                _responseCode.value = e.code()
            }
        }
    }

    fun getSalesTransaction() {
        viewModelScope.launch {
            try {
                val response = transactionRepository.getSalesTransaction()

                if (response.isSuccessful) _salesTransaction.value = response.body()?.salesTransactionData else _responseCode.value = response.code()
            } catch (e: HttpException) {
                _responseCode.value = e.code()
            }
        }
    }

    fun defaultBuyProduct() {
        _buyProductResponse.value = null
    }

    fun buyProduct(idProduct: String, type: String, weight: Int, datePickup: String) {
        viewModelScope.launch {
            try {
                val message = transactionRepository.buyProduct(idProduct, type, weight, datePickup)
                _buyProductResponse.value = message
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, BuyProductResponse::class.java)
                _buyProductResponse.value = errorBody
                Log.e(TAG, "onFailure: ${e.message}")
            }
        }
    }

    companion object {
        private val TAG = TransactionViewModel::class.java.simpleName
    }
}