package com.lelestacia.lelenime.feature_anime.util

sealed class AnimeScreen(val route: String) {
    object Dashboard : AnimeScreen("dashboard")
    object MyList: AnimeScreen("myList")
    object Explore: AnimeScreen("explore")
    object DetailAnime: AnimeScreen("detail/{animeId}") {
        fun createRoute(animeId: Int) = "detail/$animeId"
    }
}