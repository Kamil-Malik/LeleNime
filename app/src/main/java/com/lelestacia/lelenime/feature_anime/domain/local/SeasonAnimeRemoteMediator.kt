package com.lelestacia.lelenime.feature_anime.domain.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lelestacia.lelenime.core.local.SeasonAnimeDB
import com.lelestacia.lelenime.core.remote.api.AnimeApi
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeKey
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SeasonAnimeRemoteMediator @Inject constructor(
    private val animeApi: AnimeApi, private val database: SeasonAnimeDB
) : RemoteMediator<Int, SeasonAnimeCard>() {

    private val seasonAnimeDao = database.seasonAnimeDao()
    private val keySeasonAnimeDao = database.keySeasonAnimeDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, SeasonAnimeCard>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prefKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val responseData = animeApi.getCurrentSeason(page)
            val endOfPaginationReached = responseData.pagination?.hasNextPage == false

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    keySeasonAnimeDao.deleteRemoteKeys()
                    seasonAnimeDao.deleteAllSeasonAnime()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.data?.map {
                    SeasonAnimeKey(id = it.malId, prefKey = prevKey, nextKey = nextKey)
                }
                keys?.let {
                    keySeasonAnimeDao.insertAll(it)
                }
                val data = responseData.data?.map {
                    it.toAnimeCard()
                }
                seasonAnimeDao.insertListOfAnime(data ?: emptyList())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, SeasonAnimeCard>): SeasonAnimeKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            keySeasonAnimeDao.getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, SeasonAnimeCard>): SeasonAnimeKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            keySeasonAnimeDao.getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, SeasonAnimeCard>): SeasonAnimeKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keySeasonAnimeDao.getRemoteKeysId(id)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}