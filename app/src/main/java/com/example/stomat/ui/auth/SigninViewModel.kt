package com.example.stomat.ui.auth

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stomat.Prefs
import com.example.stomat.network.ApiService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty

class SigninViewModel(private val apiService: ApiService) : ViewModel() {
    val isDataValid = MutableLiveData(false)
    val isSignin = MutableLiveData(false)
    val isSignup = MutableLiveData(false)
    val isRecovery = MutableLiveData(false)
    val codeSent = MutableLiveData(false)
    val email = MutableLiveData("")
    val password = MutableLiveData("")


    init {
        email.observeForever { checkDataValid() }
        password.observeForever { checkDataValid() }
    }

    private fun checkDataValid() {
        var result = true
        if (password.value?.isEmpty() == true) result = false
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value?.trim()).matches()) {
            result = false
        }
        isDataValid.value = result
    }

    fun signin() {
        val hashMap = hashMapOf<String, String>()
        hashMap["email"] = email.value ?: ""
        hashMap["password"] = password.value ?: ""

        flow {
            try {
                emit(apiService.signin(hashMap))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            if (!it.token.isNullOrEmpty()) {
                Prefs.token = it.token
                isSignin.value = true
            }
        }.launchIn(viewModelScope)
    }

    fun signup() {
        val hashMap = hashMapOf<String, String>()
        hashMap["email"] = email.value ?: ""
        hashMap["password"] = password.value ?: ""

        flow {
            try {
                emit(apiService.signup(hashMap))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            it
        }.onEmpty {
            this
        }.launchIn(viewModelScope)
    }


    fun recovery() {
        val hashMap = hashMapOf<String, String>()
        hashMap["email"] = email.value ?: ""
        hashMap["password"] = password.value ?: ""

        flow {
            try {
                emit(apiService.recovery(hashMap))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            codeSent.value = true
        }.onEmpty {
            this
        }.launchIn(viewModelScope)
    }
}