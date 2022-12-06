package com.lelestacia.lelenime.feature_anime.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.lelenime.core.local.SeasonAnimeDB
import com.lelestacia.lelenime.core.remote.api.AnimeApi
import com.lelestacia.lelenime.core.remote.dto.anime_by_id.AnimeByIdResponse
import com.lelestacia.lelenime.core.remote.dto.anime_by_id.Data
import com.lelestacia.lelenime.feature_anime.domain.local.SeasonAnimeRemoteMediator
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import com.lelestacia.lelenime.feature_anime.domain.remote.AnimeByTitlePagingSource
import com.lelestacia.lelenime.feature_anime.domain.remote.SeasonsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: AnimeApi,
    private val seasonRemoteMediator: SeasonAnimeRemoteMediator,
    private val seasonAnimeDB: SeasonAnimeDB,
    private val seasonAnimePager: SeasonsPagingSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainRepository {

    override suspend fun getAnimeById(id: Int): AnimeByIdResponse {
        return withContext(ioDispatcher) {
            try {
                apiService.getAnimeById(id)
            } catch (e: Exception) {
                AnimeByIdResponse(data = Data())
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getSeasonPaging(): Flow<PagingData<SeasonAnimeCard>> {
        return Pager(
            config = PagingConfig(pageSize = 25, initialLoadSize = 25, prefetchDistance = 10),
            remoteMediator = seasonRemoteMediator,
            pagingSourceFactory = {
                seasonAnimeDB.seasonAnimeDao().getListOfAnimePaging()
            }
        ).flow.buffer()
    }

    override fun searchAnimeByTitle(title: String): Flow<PagingData<SeasonAnimeCard>> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 20),
            pagingSourceFactory = {
                AnimeByTitlePagingSource(apiService, title)
            }
        ).flow.buffer()
    }
}