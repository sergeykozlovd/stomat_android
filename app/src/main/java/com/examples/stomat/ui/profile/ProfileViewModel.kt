package com.examples.stomat.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.examples.stomat.App
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class ProfileViewModel(application: Application):AndroidViewModel(application) {

    private val apiService = (application as App).apiService
    fun getUserProfile() {
        flow {
            emit(
                try {
                   apiService.getUserProfile()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            )
        }.onEach {

        }.launchIn(viewModelScope)
    }
}