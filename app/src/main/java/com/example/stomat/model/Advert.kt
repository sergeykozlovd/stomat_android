package com.example.stomat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Advert:Serializable {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("image")
    lateinit var image: String

    @SerializedName("price")
    var price: Int? = null

    @SerializedName("type")
    var type: Int = 0

    @SerializedName("purchases_state")
    var purchasesState: Int? = null

    @SerializedName("purchases_id")
    var purchasesId: Int? = null
}