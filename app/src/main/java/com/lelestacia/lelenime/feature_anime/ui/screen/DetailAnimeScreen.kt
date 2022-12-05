package com.lelestacia.lelenime.feature_anime.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lelestacia.lelenime.feature_anime.ui.component.DetailAnimeHeader
import com.lelestacia.lelenime.feature_anime.ui.viewmodel.DetailAnimeViewModel
import com.lelestacia.lelenime.feature_anime.ui.viewmodel.Result

@Composable
fun DetailAnimeScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailAnimeViewModel = hiltViewModel()
) {
    viewModel.getHeader(id)
    val header = viewModel.data
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        when(header.value) {
            Result.None -> Unit
            is Result.Success -> {
                DetailAnimeHeader(
                    imageUrl = (header.value as Result.Success).animeByIdResponse.data.images.jpg.largeImageUrl,
                    title = (header.value as Result.Success).animeByIdResponse.data.title,
                    japaneseTitle = (header.value as Result.Success).animeByIdResponse.data.titleJapanese,
                    score = (header.value as Result.Success).animeByIdResponse.data.score,
                    scoredBy = (header.value as Result.Success).animeByIdResponse.data.scoredBy,
                    rank = (header.value as Result.Success).animeByIdResponse.data.rank
                )
            }
        }
    }
}