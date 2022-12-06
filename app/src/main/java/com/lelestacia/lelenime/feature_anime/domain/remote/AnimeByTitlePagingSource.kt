package com.lelestacia.lelenime.feature_anime.domain.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lelestacia.lelenime.core.remote.api.AnimeApi
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard

class AnimeByTitlePagingSource(
    private val animeApi: AnimeApi,
    private val query: String
) : PagingSource<Int, SeasonAnimeCard>() {

    override fun getRefreshKey(state: PagingState<Int, SeasonAnimeCard>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeasonAnimeCard> {
        return try {
            val currentPage = params.key ?: 1
            val apiResponse = animeApi.searchAnimeByTitle(query, currentPage, params.loadSize)
            LoadResult.Page(
                data = apiResponse.data.map {
                    it.toAnimeCard()
                },
                prevKey = if (apiResponse.pagination.currentPage == 1) null else currentPage - 1,
                nextKey = if (apiResponse.pagination.hasNextPage) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}