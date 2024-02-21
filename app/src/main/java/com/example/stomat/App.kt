package com.example.stomat

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.stomat.di.AppComponent
import com.example.stomat.di.DaggerAppComponent

class App:Application() {

    companion object {
        lateinit var prefs: SharedPreferences
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initPrefs()
        appComponent = DaggerAppComponent.create()
    }


    private fun initPrefs(){
        prefs =  getSharedPreferences("prefs", Context.MODE_PRIVATE)
        Prefs.loadPrefs()
    }

}