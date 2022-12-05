package com.lelestacia.lelenime.core.remote.dto.seasons

import com.google.gson.annotations.SerializedName

data class SeasonResponse(

    @SerializedName("data")
    val `data`: List<SeasonData>? = listOf(),

    @SerializedName("pagination")
    val pagination: Pagination? = Pagination()

)
