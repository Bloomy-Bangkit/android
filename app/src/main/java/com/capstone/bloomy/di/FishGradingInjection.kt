package com.capstone.bloomy.di

import com.capstone.bloomy.data.remote.fishgrading.FishGradingConfig
import com.capstone.bloomy.repository.FishGradingRepository

object FishGradingInjection {

    fun provideRepository(): FishGradingRepository {
        val apiService = FishGradingConfig.getApiService()

        return FishGradingRepository.getInstance(apiService)
    }
}