package com.lelestacia.lelenime.feature_anime.util

sealed class AnimeScreen(val route: String) {
    object Dashboard : AnimeScreen("dashboard")
    object MyList: AnimeScreen("myList")
    object Explore: AnimeScreen("explore")
    object DetailAnime: AnimeScreen("dashboard/{animeId}") {
        fun createRoute(animeId: String) = "dashboard/$animeId"
    }
}