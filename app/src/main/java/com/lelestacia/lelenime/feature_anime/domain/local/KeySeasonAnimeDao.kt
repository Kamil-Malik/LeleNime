package com.lelestacia.lelenime.feature_anime.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeKey

@Dao
interface KeySeasonAnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<SeasonAnimeKey>)

    @Query("SELECT * FROM season_anime_key_table WHERE id = :id")
    suspend fun getRemoteKeysId(id: Int): SeasonAnimeKey

    @Query("DELETE FROM season_anime_key_table")
    suspend fun deleteRemoteKeys()
}