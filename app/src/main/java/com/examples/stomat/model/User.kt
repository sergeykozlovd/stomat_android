package com.examples.stomat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class User:Serializable {
    @SerializedName("id")
    val id:Int?= null

    @SerializedName("email")
    val email:String? = null

    @SerializedName("name")
    val name:String? = null

    @SerializedName("surname")
    val surname:String? = null

    @SerializedName("city")
    val city:String? = null

    @SerializedName("phone")
    val phone:String? = null
}