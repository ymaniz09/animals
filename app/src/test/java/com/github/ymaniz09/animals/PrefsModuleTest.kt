package com.github.ymaniz09.animals

import android.app.Application
import com.github.ymaniz09.animals.di.PrefsModule
import com.github.ymaniz09.animals.util.SharedPreferencesHelper

class PrefsModuleTest(val mockPrefs: SharedPreferencesHelper) : PrefsModule() {

    override fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }
}