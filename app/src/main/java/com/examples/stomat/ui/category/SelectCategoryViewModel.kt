package com.examples.stomat.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.examples.stomat.App
import com.examples.stomat.model.Category
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class SelectCategoryViewModel(application: Application):AndroidViewModel(application) {
    val apiService = (application as App).apiService
    val categoriesLifeData = MutableLiveData<List<Category>>()

    fun getCategories(sectionType: Int) {
        flow {
            try {
                emit(apiService.getCategories(sectionType))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            categoriesLifeData.value = it.categories
        }.launchIn(viewModelScope)
    }
}