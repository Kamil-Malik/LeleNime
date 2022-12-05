package com.lelestacia.lelenime.core.remote.dto.anime_by_id


import com.google.gson.annotations.SerializedName

data class To(
    @SerializedName("day")
    val day: Any? = null,
    @SerializedName("month")
    val month: Any? = null,
    @SerializedName("year")
    val year: Any? = null
)