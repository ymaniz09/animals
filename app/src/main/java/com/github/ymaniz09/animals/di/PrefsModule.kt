package com.github.ymaniz09.animals.di

import android.app.Application
import com.github.ymaniz09.animals.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return SharedPreferencesHelper(app)
    }
}