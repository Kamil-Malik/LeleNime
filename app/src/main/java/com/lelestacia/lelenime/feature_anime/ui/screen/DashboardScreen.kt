package com.lelestacia.lelenime.feature_anime.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.lelestacia.lelenime.core.ui.theme.Beige
import com.lelestacia.lelenime.feature_anime.ui.component.AnimeCard
import com.lelestacia.lelenime.feature_anime.ui.viewmodel.SeasonsViewModel

@Composable
fun DashboardScreen(modifier: Modifier = Modifier, viewModel: SeasonsViewModel = hiltViewModel()) {
    val data = viewModel.getPagingData().collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) {
                    Color.Black
                } else {
                    Beige
                }
            )
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data) { pagingData ->
                pagingData?.let { anime ->
                    AnimeCard(
                        id = anime.id,
                        title = anime.title,
                        episode = anime.episode,
                        score = anime.score,
                        image = anime.image,
                        status = anime.status,
                        onClicked = {

                        }
                    )
                }
            }
        }
    }
}