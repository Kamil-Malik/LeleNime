package com.lelestacia.lelenime.di

import com.lelestacia.lelenime.core.remote.api.AnimeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLogger() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    @Provides
    @Singleton
    fun provideOkHttp(logger: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(logger)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideJikanApi(okHttpClient: OkHttpClient): AnimeApi = Retrofit.Builder()
        .baseUrl(AnimeApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build().create(AnimeApi::class.java)
}