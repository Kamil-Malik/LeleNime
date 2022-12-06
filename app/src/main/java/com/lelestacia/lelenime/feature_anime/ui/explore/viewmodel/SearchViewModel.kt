package com.lelestacia.lelenime.feature_anime.ui.explore.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import com.lelestacia.lelenime.feature_anime.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var searchQuery = mutableStateOf("")

    fun searchAnime(newQuery: String) {
        searchQuery.value = newQuery
    }

    fun getAnimeByTitle(): Flow<PagingData<SeasonAnimeCard>> {
        return mainRepository.searchAnimeByTitle(searchQuery.value)
    }
}