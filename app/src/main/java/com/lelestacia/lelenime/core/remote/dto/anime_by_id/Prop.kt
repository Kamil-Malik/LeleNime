package com.lelestacia.lelenime.core.remote.dto.anime_by_id


import com.google.gson.annotations.SerializedName

data class Prop(
    @SerializedName("from")
    val from: From = From(),
    @SerializedName("to")
    val to: To = To()
)