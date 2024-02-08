package com.example.stomat

import android.content.Context
import com.example.stomat.di.AppComponent

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }