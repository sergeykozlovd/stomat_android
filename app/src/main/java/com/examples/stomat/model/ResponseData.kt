package com.examples.stomat.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class ResponseData {
    @SerializedName("token")
    val token:String? = null

    @SerializedName("message")
    val message:String? = null

    @SerializedName("success")
    val success:Boolean? = null

    @SerializedName("adverts")
    val adverts:List<Advert>? = null

    @SerializedName("categories")
    val categories:List<Category>? = null

}