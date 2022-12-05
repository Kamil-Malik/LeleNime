package com.lelestacia.lelenime.core.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.lelenime.core.ui.theme.Orange
import com.lelestacia.lelenime.core.ui.theme.Purple
import com.lelestacia.lelenime.feature_anime.ui.component.AnimeBottomBar
import com.lelestacia.lelenime.feature_anime.ui.screen.DashboardScreen
import com.lelestacia.lelenime.feature_anime.ui.screen.DetailAnimeScreen
import com.lelestacia.lelenime.feature_anime.ui.screen.MyListScreen
import com.lelestacia.lelenime.feature_anime.ui.screen.SearchScreen
import com.lelestacia.lelenime.feature_anime.util.AnimeScreen

@Composable
fun LeleNimeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val uiState = rememberSystemUiController()
    uiState.setStatusBarColor(
        if (isSystemInDarkTheme()) {
            Purple
        } else {
            Orange
        }
    )
    uiState.setSystemBarsColor(
        if (isSystemInDarkTheme()) {
            Purple
        } else {
            Orange
        }
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LeleNime", color = Color.White)
                },
                backgroundColor = if (isSystemInDarkTheme()) {
                    Purple
                } else {
                    Orange
                }
            )
        },
        modifier = modifier,
        bottomBar = {
            AnimeBottomBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = AnimeScreen.Dashboard.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(AnimeScreen.Dashboard.route) {
                DashboardScreen(onClicked = { animeId ->
                    navController.navigate(AnimeScreen.DetailAnime.createRoute(animeId))
                })
            }
            composable(AnimeScreen.Explore.route) {
                SearchScreen()
            }
            composable(AnimeScreen.MyList.route) {
                MyListScreen()
            }
            composable(AnimeScreen.DetailAnime.route, arguments = listOf(navArgument("animeId"){
                type = NavType.IntType
            })) {
                val id = it.arguments?.getInt("animeId") ?: 0
                DetailAnimeScreen(id)
            }
        }
    }
}