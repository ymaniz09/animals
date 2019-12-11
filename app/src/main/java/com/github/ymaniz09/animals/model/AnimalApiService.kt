package com.github.ymaniz09.animals.model

import com.github.ymaniz09.animals.di.DaggerApiComponent
import javax.inject.Inject

class AnimalApiService {

    @Inject
    lateinit var api: AnimalApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getApiKey() = api.getApiKey()

    fun getAnimals(key: String) = api.getAnimals(key)
}