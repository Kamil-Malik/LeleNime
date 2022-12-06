package com.lelestacia.lelenime.core.remote.dto.anime_by_title


import com.google.gson.annotations.SerializedName

data class AnimeByTitleResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)