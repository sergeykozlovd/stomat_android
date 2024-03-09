package com.example.stomat.network

import com.example.stomat.model.ResponseData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun signup(
        @Body hashMap: HashMap<String, String>
    ): ResponseData

    @POST("login")
    suspend fun signin(
        @Body hashMap: HashMap<String, String>
    ): ResponseData

    @POST("recovery")
    suspend fun recovery(
        @Body hashMap: HashMap<String, String>
    ): ResponseData

    @POST("send_code")
    suspend fun sendRegisterCode(
        @Body hashMap: HashMap<String, String>
    ): ResponseData


    @POST("send_recovery_code")
    suspend fun sendRecoveryCode(
        @Body hashMap: HashMap<String, String>
    ): ResponseData


    @POST("user")
    suspend fun getUserProfile(): Any

    @GET("adverts")
    suspend fun getAdverts(): ResponseData

}