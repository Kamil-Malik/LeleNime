package com.lelestacia.lelenime.feature_anime.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season_anime_key_table")
data class SeasonAnimeKey(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val prefKey: Int?,

    val nextKey: Int?
)