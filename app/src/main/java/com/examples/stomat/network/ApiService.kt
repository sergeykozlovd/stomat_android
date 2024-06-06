package com.examples.stomat.network

import com.examples.stomat.model.ResponseData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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


    @GET("user")
    suspend fun getUserProfile(): Any

    @GET("adverts")
    suspend fun getAdverts(
        @Query("category") category:Int? = null
    ): ResponseData

    @GET("categories")
    suspend fun getCategories(
        @Query("parent_id") type:Int? = null
    ): ResponseData

    @POST("add_purchase_to_cart")
    suspend fun addPurchaseToCart(
        @Body hashMap: HashMap<String, Any>
    ): ResponseData

    @POST("change_purchase_state")
    suspend fun changePurchaseState(
        @Body hashMap: HashMap<String, Any>
    ): ResponseData

    @GET("get_cart_purchases")
    suspend fun  getCartPurchases(
        @Query("state") type:Int = 0
    ): ResponseData

}