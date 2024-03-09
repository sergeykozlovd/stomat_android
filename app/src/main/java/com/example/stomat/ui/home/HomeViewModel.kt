package com.example.stomat.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stomat.App
import com.example.stomat.model.Advert
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val apiService = (application as App).apiService
    val advertsLiveData = MutableLiveData<List<Advert>>()
    fun getAdverts(){
        flow {
            try {
                emit(apiService.getAdverts())
            } catch (e: Exception){
                e.printStackTrace()
            }


        }.onEach {
            advertsLiveData.value = it.adverts
        }.launchIn(viewModelScope)

    }


}