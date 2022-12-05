package com.lelestacia.lelenime.feature_anime.domain.repository

import androidx.paging.PagingData
import com.lelestacia.lelenime.core.remote.dto.anime_by_id.AnimeByIdResponse
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getAnimeById(id: Int) : AnimeByIdResponse

    fun getSeasonPaging(): Flow<PagingData<SeasonAnimeCard>>
}