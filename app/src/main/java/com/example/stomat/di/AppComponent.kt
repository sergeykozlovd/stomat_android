package com.example.stomat.di

import com.example.stomat.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
//    fun inject(application: App)
    fun inject(application: MainActivity)
}