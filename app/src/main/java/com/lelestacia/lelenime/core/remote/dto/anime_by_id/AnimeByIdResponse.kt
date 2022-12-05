package com.lelestacia.lelenime.core.remote.dto.anime_by_id


import com.google.gson.annotations.SerializedName

data class AnimeByIdResponse(
    @SerializedName("data")
    val `data`: Data = Data()
)