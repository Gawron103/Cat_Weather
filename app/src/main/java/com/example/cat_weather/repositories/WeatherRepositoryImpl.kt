package com.example.cat_weather.repositories

import com.example.cat_weather.BuildConfig
import com.example.cat_weather.api.OpenWeatherApi
import com.example.cat_weather.models.OpenWeatherCurrentWeatherModel
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherApi: OpenWeatherApi
): WeatherRepository {

    override suspend fun getWeatherForCity(name: String): Response<OpenWeatherCurrentWeatherModel> {
        return openWeatherApi.getCurrentWeatherForCity(name, BuildConfig.OPEN_WEATHER_KEY)
    }

}