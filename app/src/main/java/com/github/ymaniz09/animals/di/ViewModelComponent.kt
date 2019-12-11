package com.github.ymaniz09.animals.di

import com.github.ymaniz09.animals.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}