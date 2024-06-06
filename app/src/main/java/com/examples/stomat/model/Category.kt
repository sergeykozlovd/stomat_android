package com.examples.stomat.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category: Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String = ""

    @SerializedName("image")
    val image: String? = null

    @SerializedName("parent_id")
    val parentId: Int? = null

}