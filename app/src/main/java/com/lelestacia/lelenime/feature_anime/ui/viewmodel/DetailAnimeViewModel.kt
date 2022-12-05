package com.lelestacia.lelenime.feature_anime.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.lelenime.core.remote.dto.anime_by_id.AnimeByIdResponse
import com.lelestacia.lelenime.feature_anime.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAnimeViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    var data = mutableStateOf<Result>(Result.None)
        private set

    fun getHeader(id: Int) {
        viewModelScope.launch {
            data.value = Result.Success(repository.getAnimeById(id))
        }
    }
}

sealed class Result {
    object None : Result()
    data class Success(val animeByIdResponse: AnimeByIdResponse) : Result()
}