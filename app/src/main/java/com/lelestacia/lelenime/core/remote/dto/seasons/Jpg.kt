package com.lelestacia.lelenime.core.remote.dto.seasons


import com.google.gson.annotations.SerializedName

data class Jpg(

    @SerializedName("image_url")
    val imageUrl: String = "",

    @SerializedName("large_image_url")
    val largeImageUrl: String = "",

    @SerializedName("small_image_url")
    val smallImageUrl: String = ""

)