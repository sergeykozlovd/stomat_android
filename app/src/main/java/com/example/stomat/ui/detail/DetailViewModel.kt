package com.example.stomat.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stomat.App
import com.example.stomat.model.Advert
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class DetailViewModel(application: Application):AndroidViewModel(application) {
    private val apiService = (application as App).apiService

    fun addAdvertToCart(advert: Advert) {
        advert.id?.let { advertId ->
            val hashMap = hashMapOf<String, Any>()
            hashMap["id"] = advertId
            hashMap["count"] = 1

            flow {
                try {
                    emit(apiService.addPurchaseToCart( hashMap))
                } catch (e : Exception) {e.printStackTrace()}
            }.onEach {

            }.launchIn(viewModelScope)
        }
    }

    fun deleteFromCart(advert: Advert) {
        advert.id?.let { advertId ->
            val hashMap = hashMapOf<String, Any>()
            hashMap["advert_id"] = advertId

            flow {
                try {
                    emit(apiService.changePurchaseState( hashMap))
                } catch (e : Exception) {e.printStackTrace()}
            }.onEach {

            }.launchIn(viewModelScope)
        }
    }
}