package com.lelestacia.lelenime.core.remote.dto.seasons


import com.google.gson.annotations.SerializedName
import com.lelestacia.lelenime.feature_anime.domain.model.SeasonAnimeCard

data class SeasonData(

    @SerializedName("episodes")
    val episodes: Int? = 0,

    @SerializedName("images")
    val images: Images = Images(),

    @SerializedName("mal_id")
    val malId: Int = 0,

    @SerializedName("score")
    val score: Double = 0.0,

    @SerializedName("status")
    val status: String = "",

    @SerializedName("title")
    val title: String = "",

    ) {

    fun toAnimeCard() : SeasonAnimeCard {
        return SeasonAnimeCard(
            id = malId,
            title = title,
            episode = episodes ?: 0,
            score = score,
            image = images.jpg.imageUrl,
            status = status
        )
    }
}