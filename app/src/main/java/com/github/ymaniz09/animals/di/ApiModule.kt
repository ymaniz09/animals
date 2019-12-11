package com.github.ymaniz09.animals.di

import com.github.ymaniz09.animals.model.AnimalApi
import com.github.ymaniz09.animals.model.AnimalApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideAnimalApi(): AnimalApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimalApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net/"
    }

    @Provides
    fun provideAnimalApiService(): AnimalApiService {
        return AnimalApiService()
    }
}