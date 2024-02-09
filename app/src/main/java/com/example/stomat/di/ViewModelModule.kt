package com.example.stomat.di

import androidx.lifecycle.ViewModelProvider
import com.example.stomat.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory) :ViewModelProvider.Factory{
        return factory
    }
}