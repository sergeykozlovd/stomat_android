package com.example.stomat

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.stomat.network.ApiService
import com.example.stomat.network.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App:Application() {

    companion object {
        lateinit var prefs: SharedPreferences
    }


    lateinit var apiService: ApiService
//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initPrefs()
        initRetrofit()
//        appComponent = DaggerAppComponent.create()
    }

    private fun initRetrofit(){
        val authorizationInterceptor = AuthorizationInterceptor()

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Const.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)


    }


    private fun initPrefs(){
        prefs =  getSharedPreferences("prefs", Context.MODE_PRIVATE)
        Prefs.loadPrefs()
    }

}