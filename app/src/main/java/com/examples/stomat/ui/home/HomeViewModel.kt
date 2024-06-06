package com.examples.stomat.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.examples.stomat.App
import com.examples.stomat.model.Advert
import com.examples.stomat.model.Category
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val apiService = (application as App).apiService
    val advertsLiveData = MutableLiveData<List<Advert>>()
    val sectionsLiveData = MutableLiveData<List<Category>>()

    fun getAdverts() {
        flow {
            try {
                emit(apiService.getAdverts(0))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            advertsLiveData.value = it.adverts
        }.launchIn(viewModelScope)
    }

    fun getCategories() {
        flow {
            try {
                emit(apiService.getCategories())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }.onEach {
            sectionsLiveData.value = it.categories
        }.launchIn(viewModelScope)
    }

    fun fetchData(){
        viewModelScope.launch {
            try {
                val adverts = apiService.getAdverts(0)
                advertsLiveData.value = adverts.adverts

                val sections = apiService.getCategories()
                sectionsLiveData.value = sections.categories

            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
}