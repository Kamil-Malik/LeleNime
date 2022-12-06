package com.lelestacia.lelenime.feature_anime.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_anime_table" )
data class SeasonAnimeCard(

    @PrimaryKey
    val id: Int,

    val title: String,

    val episode: Int,

    val score: Double,

    val image: String,

    val status: String,
)
