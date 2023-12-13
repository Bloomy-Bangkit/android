package com.capstone.bloomy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.bloomy.data.response.DeleteProductData
import com.capstone.bloomy.data.response.EditProfileResponse
import com.capstone.bloomy.data.response.ProductByIdData
import com.capstone.bloomy.data.response.ProductByUsernameData
import com.capstone.bloomy.repository.ProductRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<List<ProductByUsernameData>>()
    val product: LiveData<List<ProductByUsernameData>> = _product

    private val _detailProduct = MutableLiveData<ProductByIdData>()
    val detailProduct: LiveData<ProductByIdData> = _detailProduct

    private val _responseCode = MutableLiveData<Int>()

    fun getProductByUsername(username: String) {
        viewModelScope.launch {
            try {
                val response = productRepository.getProductByUsername(username)

                if (response.isSuccessful) _product.value = response.body()?.productByUsernameData else _responseCode.value = response.code()
            } catch (e: HttpException) {
                _responseCode.value = e.code()
            }
        }
    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            try {
                val response = productRepository.getProductById(id)

                if (response.isSuccessful) _detailProduct.value = response.body()?.productByIdData else _responseCode.value = response.code()
            } catch (e: HttpException) {
                _responseCode.value = e.code()
            }
        }
    }

    fun addProduct(file: File, nama: String, grade: String, price: Number, weight: Number, description: String) = productRepository.addProduct(file, nama, grade, price, weight, description)

    fun editProduct(id: String, nama: String, grade: String, price: Number, weight: Number, description: String) = productRepository.editProduct(id, nama, grade, price, weight, description)

    fun editPhotoProduct(id: String, file: File) = productRepository.editPhotoProduct(id, file)

    fun deleteProduct(id: String) = productRepository.deleteProduct(id)
}