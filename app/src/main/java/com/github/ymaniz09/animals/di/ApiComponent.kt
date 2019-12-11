package com.github.ymaniz09.animals.di

import com.github.ymaniz09.animals.model.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AnimalApiService)
}