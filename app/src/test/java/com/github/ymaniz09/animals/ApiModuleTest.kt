package com.github.ymaniz09.animals

import com.github.ymaniz09.animals.di.ApiModule
import com.github.ymaniz09.animals.model.AnimalApiService

class ApiModuleTest(val mockService: AnimalApiService) : ApiModule() {

    override fun provideAnimalApiService(): AnimalApiService {
        return mockService
    }
}