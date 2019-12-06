package com.github.ymaniz09.animals.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.ymaniz09.animals.model.Animal

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getAnimals()
    }

    private fun getAnimals() {
        val animal1 = Animal("Alligator")
        val animal2 = Animal("Cat")
        val animal3 = Animal("Bee")
        val animal4 = Animal("Dog")
        val animal5 = Animal("Elephant")
        val animal6 = Animal("Flamingo")
        val animal7 = Animal("Frog")

        val animalList = arrayListOf(animal1, animal2, animal3, animal4, animal5, animal6, animal7)

        animals.value = animalList
        loadError.value = false
        loading.value = false
    }
}