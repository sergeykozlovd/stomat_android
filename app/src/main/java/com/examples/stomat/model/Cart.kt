package com.examples.stomat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cart:Serializable {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("price")
    var price: Int? = null

    @SerializedName("type")
    var type: Int = 0

    @SerializedName("purchases_state")
    var purchasesState: Int? = null

    @SerializedName("purchases_id")
    var purchasesId: Int? = null
}