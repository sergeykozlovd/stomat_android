package com.examples.stomat.ui.cart

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

class CartViewModel(application: Application):AndroidViewModel(application) {
    private val apiService = (application as App).apiService
    val cartPurchases = MutableLiveData<List<Advert>>()

    fun getCartPurchases(){
        flow {
            try {
                emit(
                    apiService.getCartPurchases()
                )
            } catch (e:Exception){e.printStackTrace()}
        }.onEach {
            cartPurchases.value = it.adverts
        }.launchIn(viewModelScope)
    }
}