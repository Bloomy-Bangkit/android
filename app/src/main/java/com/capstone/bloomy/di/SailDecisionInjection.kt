package com.capstone.bloomy.di

import com.capstone.bloomy.data.remote.saildecision.SailDecisionConfig
import com.capstone.bloomy.repository.SailDecisionRepository

object SailDecisionInjection {

    fun provideRepository(): SailDecisionRepository {
        val apiService = SailDecisionConfig.getApiService()

        return SailDecisionRepository.getInstance(apiService)
    }
}