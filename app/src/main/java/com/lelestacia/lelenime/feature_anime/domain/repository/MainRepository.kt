package com.lelestacia.lelenime.feature_anime.domain.repository

import androidx.paging.PagingData
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getSeasonPaging(): Flow<PagingData<SeasonAnimeCard>>
}