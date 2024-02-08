package com.example.stomat.network

import com.example.stomat.model.ResponseData
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signup")
    suspend fun signup(
        @Body hashMap: HashMap<String,String>
    ) : ResponseData

    @POST("signin")
    suspend fun signin(
        @Body hashMap: HashMap<String,String>
    ) : ResponseData


    @POST("get_user_profile")
    suspend fun getUserProfile() : Any

}