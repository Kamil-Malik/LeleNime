package com.lelestacia.lelenime.feature_anime.ui.explore.screen

sealed class ExploreAnimeScreenEvent {
    data class OnValueChanged(val newValue : String) : ExploreAnimeScreenEvent()
    object OnSearch : ExploreAnimeScreenEvent()
}
