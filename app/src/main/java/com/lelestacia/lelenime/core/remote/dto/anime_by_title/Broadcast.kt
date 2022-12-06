package com.lelestacia.lelenime.core.remote.dto.anime_by_title


import com.google.gson.annotations.SerializedName

data class Broadcast(
    @SerializedName("day")
    val day: String? = null,
    @SerializedName("string")
    val string: String? = null,
    @SerializedName("time")
    val time: String? = null,
    @SerializedName("timezone")
    val timezone: String? = null
)