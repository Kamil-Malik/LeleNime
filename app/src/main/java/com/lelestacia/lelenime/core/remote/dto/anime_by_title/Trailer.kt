package com.lelestacia.lelenime.core.remote.dto.anime_by_title


import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("embed_url")
    val embedUrl: String? = "",
    @SerializedName("images")
    val images: ImagesX = ImagesX(),
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("youtube_id")
    val youtubeId: String? = ""
)