package com.github.ymaniz09.animals.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.ymaniz09.animals.TAG
import com.github.ymaniz09.animals.model.Animal
import com.github.ymaniz09.animals.model.AnimalApiService
import com.github.ymaniz09.animals.model.ApiKey
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()
    private val apiService = AnimalApiService()

    fun refresh() {
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
                        Log.e(TAG, "Error calling getAnimals()", e)
                        loadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}