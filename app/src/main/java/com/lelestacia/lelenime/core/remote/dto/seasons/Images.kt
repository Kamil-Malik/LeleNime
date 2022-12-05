package com.lelestacia.lelenime.core.remote.dto.seasons


import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("jpg")
    val jpg: Jpg = Jpg(),

)