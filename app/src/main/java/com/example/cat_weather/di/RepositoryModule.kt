package com.example.cat_weather.di

import com.example.cat_weather.apis.OpenWeatherApi
import com.example.cat_weather.apis.UnsplashApi
import com.example.cat_weather.repositories.WeatherRepository
import com.example.cat_weather.repositories.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        openWeatherApi: OpenWeatherApi,
        unsplashApi: UnsplashApi
    ): WeatherRepository {
        return WeatherRepositoryImpl(openWeatherApi, unsplashApi)
    }

}