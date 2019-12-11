package com.github.ymaniz09.animals.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.ymaniz09.animals.di.AppModule
import com.github.ymaniz09.animals.di.DaggerViewModelComponent
import com.github.ymaniz09.animals.model.Animal
import com.github.ymaniz09.animals.model.AnimalApiService
import com.github.ymaniz09.animals.model.ApiKey
import com.github.ymaniz09.animals.util.SharedPreferencesHelper
import com.github.ymaniz09.animals.util.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()
    private var invalidApiKey = false

    @Inject
    lateinit var apiService: AnimalApiService

    @Inject
    lateinit var prefs: SharedPreferencesHelper

    init {
        DaggerViewModelComponent
            .builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

    fun refresh() {
        loading.value = true

        invalidApiKey = false

        val key = prefs.getApiKey()
        if (key.isNullOrEmpty()) {
            getKey()
        } else {
            getAnimals(key)
        }
    }

    fun hardRefresh() {
        loading.value = true
        getKey()
    }

    private fun getKey() {
        disposable.add(
            apiService.getApiKey()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ApiKey>() {
                    override fun onSuccess(apiKey: ApiKey) {
                        if (apiKey.key.isNullOrEmpty()) {
                            loadError.value = true
                            loading.value = false
                        } else {
                            prefs.saveApiKey(apiKey.key)
                            getAnimals(apiKey.key)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Error calling getApiKey()", e)
                        loadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    private fun getAnimals(key: String) {
        disposable.add(
            apiService.getAnimals(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Animal>>() {
                    override fun onSuccess(animalList: List<Animal>) {
                        if (animalList.isNullOrEmpty()) {
                            loadError.value = true
                            loading.value = false
                        } else {
                            animals.value = animalList
                            loadError.value = false
                            loading.value = false
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (!invalidApiKey) {
                            invalidApiKey = true
                            getKey()
                        } else {
                            Log.e(TAG, "Error calling getAnimals()", e)
                            loadError.value = true
                            loading.value = false
                            animals.value = null
                        }
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}