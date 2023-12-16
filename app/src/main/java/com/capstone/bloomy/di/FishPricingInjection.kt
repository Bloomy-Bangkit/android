package com.capstone.bloomy.di

import com.capstone.bloomy.data.remote.fishpricing.FishPricingConfig
import com.capstone.bloomy.repository.FishPricingRepository

object FishPricingInjection {

    fun provideRepository(): FishPricingRepository {
        val apiService = FishPricingConfig.getApiService()

        return FishPricingRepository.getInstance(apiService)
    }
}