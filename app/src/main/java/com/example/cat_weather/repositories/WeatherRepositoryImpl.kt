package com.example.cat_weather.repositories

import com.example.cat_weather.BuildConfig
import com.example.cat_weather.apis.OpenWeatherApi
import com.example.cat_weather.apis.UnsplashApi
import com.example.cat_weather.models.unsplashmodel.UnsplashModel
import com.example.cat_weather.models.weathermodel.OpenWeatherCurrentWeatherModel
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
    private val unsplashApi: UnsplashApi
): WeatherRepository {

    override suspend fun getWeatherForCity(name: String): Response<OpenWeatherCurrentWeatherModel> {
        return openWeatherApi.getCurrentWeatherForCity(name, BuildConfig.OPEN_WEATHER_KEY)
    }

    override suspend fun getPhotoOfCity(name: String): Response<UnsplashModel> {
        return unsplashApi.getPhotoForCity(name, "portrait")
    }

}