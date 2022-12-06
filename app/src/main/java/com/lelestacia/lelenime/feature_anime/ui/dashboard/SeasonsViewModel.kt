package com.lelestacia.lelenime.feature_anime.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lelestacia.lelenime.feature_anime.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeasonsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getPagingData() = mainRepository.getSeasonPaging().cachedIn(viewModelScope)
}