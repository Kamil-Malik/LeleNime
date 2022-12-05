package com.lelestacia.lelenime.core.remote.api

import com.lelestacia.lelenime.core.remote.dto.anime_by_id.AnimeByIdResponse
import com.lelestacia.lelenime.core.remote.dto.seasons.SeasonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("seasons/now")
    suspend fun getCurrentSeason(@Query("page") page: Int = 2) : SeasonResponse

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int) : AnimeByIdResponse

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}