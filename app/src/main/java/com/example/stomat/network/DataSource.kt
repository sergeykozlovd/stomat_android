package com.example.stomat.network

import com.example.stomat.Const
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DataSource(val apiService: ApiService) {


    private fun getData(vararg param: String)  :HashMap<String,String>{

        val hashMap  = hashMapOf<String,String>()

        if (param.size % 2 != 1 ) {
            var key = ""
            var value:String

            param.forEachIndexed { index, s ->
                if (index % 2 == 0){
                    key = s
                } else {
                    value = s
                    hashMap[key] = value
                }
            }
        }

        return hashMap
    }

    fun signup(email: String, password: String){
        val data = getData(
            Const.EMAIL, email,
            Const.PASSWORD, password
        )

        flow {
            try {
                emit(apiService.signup(data))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.onEach {

        }.launchIn(MainScope())
    }

    fun signin(email: String, password: String){
        val data = getData(
            Const.EMAIL, email,
            Const.PASSWORD, password
        )

        flow {
            try {
                emit(apiService.signin(data))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.onEach {

        }.launchIn(MainScope())
    }


    fun getUserProfile(callback:(success:Boolean)->Unit){

        flow {
            try {
                emit(apiService.getUserProfile())
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.onEach {

            callback(true)
        }.launchIn(MainScope())
    }


}