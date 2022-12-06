package com.lelestacia.lelenime.core.remote.dto.anime_by_title


import com.google.gson.annotations.SerializedName

data class To(
    @SerializedName("day")
    val day: Int? = null,
    @SerializedName("month")
    val month: Int? = null,
    @SerializedName("year")
    val year: Int? = null
)