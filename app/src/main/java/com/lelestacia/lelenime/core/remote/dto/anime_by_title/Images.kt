package com.lelestacia.lelenime.core.remote.dto.anime_by_title


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("jpg")
    val jpg: Jpg = Jpg(),
    @SerializedName("webp")
    val webp: Webp = Webp()
)