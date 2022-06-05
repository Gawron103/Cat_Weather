package com.example.cat_weather.repositories

import com.example.cat_weather.models.unsplashmodel.UnsplashModel
import com.example.cat_weather.models.weathermodel.OpenWeatherCurrentWeatherModel
import retrofit2.Response


interface WeatherRepository {

    suspend fun getWeatherForCity(name: String): Response<OpenWeatherCurrentWeatherModel>

    suspend fun getPhotoOfCity(name: String): Response<UnsplashModel>

}