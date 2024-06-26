package com.examples.stomat.ui.auth

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.examples.stomat.App
import com.examples.stomat.Prefs
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty

class SigninViewModel(application: Application) : AndroidViewModel(application) {

    val apiService = (application as App).apiService
    val isDataValid = MutableLiveData(false)
    val isSignin = MutableLiveData(false)
    val codeRecoverySent = MutableLiveData(false)
    val codeRegisterSent = MutableLiveData(false)
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val surname = MutableLiveData("")
    val phone = MutableLiveData("")
    val city = MutableLiveData("")


    init {
        email.observeForever { checkDataValid() }
        password.observeForever { checkDataValid() }
        name.observeForever { checkDataValid() }
        surname.observeForever { checkDataValid() }
        phone.observeForever { checkDataValid() }
        city.observeForever { checkDataValid() }
    }

    private fun checkDataValid() {
        var result = true
        if (password.value?.isEmpty() == true) result = false
        if (isSignin.value == false){
            if (name.value?.isEmpty() == true) result = false
            if (surname.value?.isEmpty() == true) result = false
            if (phone.value?.isEmpty() == true) result = false
            if (city.value?.isEmpty() == true) result = false
        }
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
                Prefs.savePrefs()
                isSignin.value = true
            }
        }.launchIn(viewModelScope)
    }

    fun signup() {
        val hashMap = hashMapOf<String, String>()
        hashMap["email"] = email.value ?: ""
        hashMap["password"] = password.value ?: ""
        hashMap["name"] = name.value ?: ""
        hashMap["surname"] = surname.value ?: ""
        hashMap["phone"] = phone.value ?: ""
        hashMap["city"] = city.value ?: ""

        flow {
            try {
                emit(apiService.signup(hashMap))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.onEach {
            codeRegisterSent.value = true
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
            codeRecoverySent.value = true
        }.onEmpty {
            this
        }.launchIn(viewModelScope)
    }
}