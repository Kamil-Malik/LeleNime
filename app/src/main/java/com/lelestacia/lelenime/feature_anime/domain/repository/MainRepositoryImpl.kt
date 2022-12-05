package com.lelestacia.lelenime.feature_anime.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.lelenime.core.remote.api.AnimeApi
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import com.lelestacia.lelenime.feature_anime.domain.remote.SeasonsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: AnimeApi,
    private val seasonsPagingSource: SeasonsPagingSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainRepository {

    override fun getSeasonPaging(): Flow<PagingData<SeasonAnimeCard>> {
        return Pager(
            config = PagingConfig(25),
            pagingSourceFactory = {
                seasonsPagingSource
            }
        ).flow.buffer()
    }
}