package com.lelestacia.lelenime.feature_anime.domain.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard

@Dao
interface SeasonAnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfAnime(anime: List<SeasonAnimeCard>)

    @Query("SELECT * FROM list_anime_table")
    fun getListOfAnimePaging(): PagingSource<Int, SeasonAnimeCard>

    @Query("DELETE FROM list_anime_table")
    suspend fun deleteAllSeasonAnime()
}