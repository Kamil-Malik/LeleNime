package com.lelestacia.lelenime.feature_anime.domain.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.lelenime.core.remote.api.AnimeApi
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeasonsPagingSource @Inject constructor(
    private val animeApi: AnimeApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PagingSource<Int, SeasonAnimeCard>() {

    override fun getRefreshKey(state: PagingState<Int, SeasonAnimeCard>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeasonAnimeCard> {
        return withContext(ioDispatcher) {
            try {
                val currentPage = params.key ?: 1
                val apiResponse = animeApi.getCurrentSeason(currentPage)
                LoadResult.Page(
                    data = apiResponse.data?.map {
                        it.toAnimeCard()
                    } ?: emptyList(),
                    prevKey = if (apiResponse.pagination?.currentPage == 1) null else currentPage - 1,
                    nextKey = if (apiResponse.pagination?.hasNextPage == true) currentPage + 1 else null
                )
            } catch (e: Throwable) {
                LoadResult.Error(e)
            }
        }
    }
}