package com.example.stomat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stomat.network.ApiService
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService):ViewModel() {

}