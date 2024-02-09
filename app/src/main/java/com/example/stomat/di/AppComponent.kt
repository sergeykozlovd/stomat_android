package com.example.stomat.di

import com.example.stomat.MainActivity
import com.example.stomat.ui.auth.CodeFragment
import com.example.stomat.ui.auth.SigninFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {
//    fun inject(application: App)
    fun inject(application: MainActivity)
    fun inject(fragment: SigninFragment)
    fun inject(fragment: CodeFragment)
}