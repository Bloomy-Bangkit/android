package com.capstone.bloomy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.bloomy.data.model.UserModel
import com.capstone.bloomy.data.response.SignInResponse
import com.capstone.bloomy.data.response.SignUpResponse
import com.capstone.bloomy.repository.AuthenticationRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {

    private val _signUpResponse = MutableLiveData<SignUpResponse?>()
    val signUpResponse: LiveData<SignUpResponse?> = _signUpResponse

    private val _signInResponse = MutableLiveData<UserModel?>()
    val signInResponse: LiveData<UserModel?> = _signInResponse

    private val _invalidSignInResponse = MutableLiveData<SignInResponse?>(null)
    val invalidSignInResponse: LiveData<SignInResponse?> = _invalidSignInResponse

    fun defaultSignUp() {
        _signUpResponse.value = null
    }

    fun defaultSignIn() {
        _invalidSignInResponse.value = null
    }

    fun signUp(email: String, username: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            try {
                val message = authenticationRepository.signUp(email, username, password, confirmPassword)
                _signUpResponse.value = message
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, SignUpResponse::class.java)
                _signUpResponse.value = errorBody
                Log.e(TAG, "onFailure: ${e.message}")
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authenticationRepository.signIn(email, password)
                val userModel = UserModel()

                response.signInData.apply {
                    userModel.token = token
                }

                _signInResponse.value = userModel
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, SignInResponse::class.java)
                _invalidSignInResponse.value = errorBody
            }
        }
    }

    companion object {
        private val TAG = AuthenticationViewModel::class.java.simpleName
    }
}