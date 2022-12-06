package com.lelestacia.lelenime.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lelestacia.lelenime.feature_anime.domain.local.KeySeasonAnimeDao
import com.lelestacia.lelenime.feature_anime.domain.local.SeasonAnimeDao
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeKey

@Database(
    entities = [SeasonAnimeCard::class, SeasonAnimeKey::class],
    version = 1
)
abstract class SeasonAnimeDB : RoomDatabase() {

    abstract fun seasonAnimeDao(): SeasonAnimeDao

    abstract fun keySeasonAnimeDao(): KeySeasonAnimeDao
}