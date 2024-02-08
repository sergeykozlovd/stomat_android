package com.example.stomat.ui.auth.signin

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SigninViewModel: ViewModel() {
    val isDataValid = MutableLiveData(false)
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    init {
        email.observeForever {  checkDataValid() }
        password.observeForever {  checkDataValid() }
    }

    private fun checkDataValid(){
        var result = true
        if (password.value?.isEmpty() == true)  result = false
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value?.trim()).matches()){
            result = false
        }
        isDataValid.value = result
    }

    fun signin(){

    }

}