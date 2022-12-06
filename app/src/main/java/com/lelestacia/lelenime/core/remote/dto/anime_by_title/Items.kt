package com.lelestacia.lelenime.core.remote.dto.anime_by_title


import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("per_page")
    val perPage: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)