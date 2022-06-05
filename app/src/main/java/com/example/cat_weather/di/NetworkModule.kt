package com.example.cat_weather.di

import com.example.cat_weather.apis.OpenWeatherApi
import com.example.cat_weather.apis.UnsplashApi
import com.example.cat_weather.utils.OPEN_WEATHER_MAPS_BASE_URL
import com.example.cat_weather.utils.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("WeatherRetrofit")
    fun provideOpenWeatherRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_MAPS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherApi(@Named("WeatherRetrofit") retrofit: Retrofit): OpenWeatherApi {
        return retrofit.create(OpenWeatherApi::class.java)
    }

    @Singleton
    @Provides
    @Named("UnsplashRetrofit")
    fun provideUnsplashRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUnsplashApi(@Named("UnsplashRetrofit") retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }

}