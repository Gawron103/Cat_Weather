package com.example.cat_weather.repositories

import com.example.cat_weather.models.OpenWeatherCurrentWeatherModel
import retrofit2.Response


interface WeatherRepository {

    suspend fun getWeatherForCity(name: String): Response<OpenWeatherCurrentWeatherModel>

}