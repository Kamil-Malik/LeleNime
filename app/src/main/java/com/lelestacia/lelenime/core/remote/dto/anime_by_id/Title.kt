package com.lelestacia.lelenime.core.remote.dto.anime_by_id


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: String = ""
)