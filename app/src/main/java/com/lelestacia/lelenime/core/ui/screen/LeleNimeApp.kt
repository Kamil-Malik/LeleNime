package com.lelestacia.lelenime.core.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
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
import com.lelestacia.lelenime.feature_anime.ui.dashboard.DashboardScreen
import com.lelestacia.lelenime.feature_anime.ui.explore.screen.ExploreAnimeScreen
import com.lelestacia.lelenime.feature_anime.ui.screen.DetailAnimeScreen
import com.lelestacia.lelenime.feature_anime.ui.screen.MyListScreen
import com.lelestacia.lelenime.feature_anime.util.AnimeScreen

@Composable
fun LeleNimeApp(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    rememberScaffoldState()
    val currentRoute = navController.currentDestination?.route ?: ""
    val uiState = rememberSystemUiController()
    val backgroundColor = when (isSystemInDarkTheme()) {
        true -> Purple
        false -> Orange
    }
    uiState.apply {
        setStatusBarColor(backgroundColor)
        setSystemBarsColor(backgroundColor)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LeleNime", color = Color.White)
                },
                backgroundColor = backgroundColor
            )
        },
        modifier = modifier,
        bottomBar = {
            if(!currentRoute.contains(AnimeScreen.DetailAnime.route.substringBefore("/"))) {
                AnimeBottomBar(navController = navController, backgroundColor = backgroundColor)
            }
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
                ExploreAnimeScreen(onClicked = { animeId ->
                    navController.navigate(AnimeScreen.DetailAnime.createRoute(animeId))
                })
            }
            composable(AnimeScreen.MyList.route) {
                MyListScreen()
            }
            composable(AnimeScreen.DetailAnime.route, arguments = listOf(navArgument("animeId") {
                type = NavType.IntType
            })) {
                val id = it.arguments?.getInt("animeId") ?: 0
                DetailAnimeScreen(id)
            }
        }
    }

    /*NavHost(
        navController = navController,
        startDestination = AnimeScreen.Dashboard.route,
        modifier = modifier
    ) {
        composable(AnimeScreen.Dashboard.route) {
            AnimeScaffoldLayout(
                screen = {
                    DashboardScreen(onClicked = { animeId ->
                        navController.navigate(AnimeScreen.DetailAnime.createRoute(animeId))
                    })
                },
                navController = navController,
                backgroundColor = backgroundColor,
            )
        }
        composable(AnimeScreen.Explore.route) {
            AnimeScaffoldLayout(
                screen = {
                    ExploreAnimeScreen(onClicked = { animeId ->
                        navController.navigate(AnimeScreen.DetailAnime.createRoute(animeId))
                    })
                }, navController = navController, backgroundColor = backgroundColor
            )
        }
        composable(AnimeScreen.MyList.route) {
            AnimeScaffoldLayout(screen = {
                MyListScreen()
            }, navController = navController, backgroundColor = backgroundColor)
        }
        composable(
            route = AnimeScreen.DetailAnime.route, arguments = listOf(navArgument("animeId") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("animeId") ?: 0
            DetailAnimeScreen(id)
        }
    }*/
}