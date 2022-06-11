package com.example.cat_weather.repositories

import com.example.cat_weather.apis.ResponseState
import com.example.cat_weather.models.forecastmodel.ForecastModel
import com.example.cat_weather.models.weatherbycordsmodel.WeatherByCordsModel
import com.example.cat_weather.models.weatherbynamemodel.WeatherByNameModel


interface WeatherRepository {

    suspend fun getWeatherByCords(lat: Double, lon: Double): ResponseState<WeatherByCordsModel?>

    suspend fun getWeatherByName(name: String): ResponseState<WeatherByNameModel?>

    suspend fun getForecastByCords(lat: Double, lon: Double): ResponseState<ForecastModel?>

    suspend fun getCityImage(name: String): ResponseState<String?>

}