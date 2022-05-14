package com.example.cat_weather.api

import com.example.cat_weather.models.OpenWeatherCurrentWeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherForCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
    ): Response<OpenWeatherCurrentWeatherModel>

}