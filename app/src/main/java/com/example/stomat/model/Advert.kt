package com.example.stomat.model

import com.google.gson.annotations.SerializedName

class Advert {
    @SerializedName("title")
    lateinit var title: String

    @SerializedName("image")
    lateinit var image: String
}