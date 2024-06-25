package com.examples.stomat.ui.profile

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.examples.stomat.App
import com.examples.stomat.model.User
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = (application as App).apiService
    val userLiveData = MutableLiveData<User>()

    fun getUserProfile() {
        flow {
            try {
                emit(
                    apiService.getUserProfile()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            userLiveData.value = it.user
        }.launchIn(viewModelScope)
    }

    fun changeUser(
        name: String?,
        surname: Editable?,
        phone: Editable?,
        city: Editable?
    ) {
        val hashMap =  HashMap<String, Any>().apply {
            name?.let { put("name", it) }
//            surname?.let { put("name", it) }
//            phone?.let { put("name", it) }
//            city?.let { put("name", it) }
        }

        flow {
//            try {
                emit(
                    try {
                        apiService.changeUser( hashMap )
                    } catch (e:Exception) {
                        e.printStackTrace()
                    }

                )
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        }.onEach {
            it
        }.launchIn(viewModelScope)

    }
}