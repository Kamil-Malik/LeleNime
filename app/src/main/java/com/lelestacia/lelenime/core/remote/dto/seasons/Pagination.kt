package com.lelestacia.lelenime.core.remote.dto.seasons

import com.google.gson.annotations.SerializedName

data class Pagination(

    @SerializedName("current_page")
    val currentPage: Int? = 0,

    @SerializedName("has_next_page")
    val hasNextPage: Boolean? = false,

    @SerializedName("last_visible_page")
    val lastVisiblePage: Int? = 0
)