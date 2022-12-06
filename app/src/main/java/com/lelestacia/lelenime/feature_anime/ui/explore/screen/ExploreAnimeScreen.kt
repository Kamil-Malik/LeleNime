package com.lelestacia.lelenime.feature_anime.ui.explore.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.lelestacia.lelenime.core.ui.theme.Beige
import com.lelestacia.lelenime.feature_anime.ui.component.AnimeCard
import com.lelestacia.lelenime.feature_anime.ui.component.CustomizedSearchField
import com.lelestacia.lelenime.feature_anime.ui.explore.viewmodel.SearchViewModel

@Composable
fun ExploreAnimeScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onClicked: (Int) -> Unit
) {
    val backgroundColor = when (isSystemInDarkTheme()) {
        true -> Color.Black
        false -> Beige
    }
    val data = viewModel.getAnimeByTitle().collectAsLazyPagingItems()
    var searchFieldValue by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomizedSearchField(
            value = searchFieldValue,
            onValueChanged = { newSearchQuery ->
                searchFieldValue = newSearchQuery
            },
            onSearchIconClicked = {
                viewModel.searchAnime(searchFieldValue)
            })
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
                            onClicked(it)
                        }
                    )
                }
            }
        }
    }
}