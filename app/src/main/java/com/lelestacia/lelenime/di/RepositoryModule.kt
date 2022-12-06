package com.lelestacia.lelenime.di

import com.lelestacia.lelenime.core.local.SeasonAnimeDB
import com.lelestacia.lelenime.core.remote.api.AnimeApi
import com.lelestacia.lelenime.feature_anime.domain.local.SeasonAnimeRemoteMediator
import com.lelestacia.lelenime.feature_anime.domain.remote.SeasonsPagingSource
import com.lelestacia.lelenime.feature_anime.domain.repository.MainRepository
import com.lelestacia.lelenime.feature_anime.domain.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSeasonPagingSource(animeApi: AnimeApi) =
        SeasonsPagingSource(animeApi)

    @Provides
    @Singleton
    fun provideSeasonRemoteMediator(
        animeApi: AnimeApi,
        seasonAnimeDB: SeasonAnimeDB
    ): SeasonAnimeRemoteMediator =
        SeasonAnimeRemoteMediator(animeApi, seasonAnimeDB)

    @Provides
    @Singleton
    fun provideMainRepository(
        animeApi: AnimeApi,
        seasonAnimeRemoteMediator: SeasonAnimeRemoteMediator,
        seasonAnimeDB: SeasonAnimeDB,
        seasonsPagingSource: SeasonsPagingSource
    ): MainRepository =
        MainRepositoryImpl(animeApi, seasonAnimeRemoteMediator, seasonAnimeDB, seasonsPagingSource)
}