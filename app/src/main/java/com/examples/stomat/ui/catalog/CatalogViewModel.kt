package com.examples.stomat.ui.catalog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.examples.stomat.App
import com.examples.stomat.model.Advert
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class CatalogViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = (application as App).apiService
    val advertsLiveData = MutableLiveData<List<Advert>>()

    fun getAdverts(categoryId:Int?){
        flow {
            try {
                emit(apiService.getAdverts(categoryId))
            } catch (e:Exception){
                e.printStackTrace()
            }
        }.onEach {
            advertsLiveData.value = it.adverts
        }.launchIn(viewModelScope)
    }
}