package com.lelestacia.lelenime.core.remote.dto.anime_by_id


import com.google.gson.annotations.SerializedName

data class Aired(
    @SerializedName("from")
    val from: String = "",
    @SerializedName("prop")
    val prop: Prop = Prop(),
    @SerializedName("string")
    val string: String = "",
    @SerializedName("to")
    val to: Any? = null
)