package com.github.ymaniz09.animals.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AnimalApiService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AnimalApi::class.java)

    companion object {
        const val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net/"
    }

    fun getApiKey() = api.getApiKey()


    fun getAnimals(key: String) = api.getAnimals(key)
    
}