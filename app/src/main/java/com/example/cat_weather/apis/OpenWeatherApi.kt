package com.example.cat_weather.apis

import com.example.cat_weather.models.airpollutionmodel.AirPollutionModel
import com.example.cat_weather.models.forecastmodel.ForecastModel
import com.example.cat_weather.models.weatherbycordsmodel.WeatherByCordsModel
import com.example.cat_weather.models.weatherbynamemodel.WeatherByNameModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getWeatherForCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
    ): Response<WeatherByNameModel>

    @GET("/data/2.5/forecast")
    suspend fun getForecastForCords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): Response<ForecastModel>

    @GET("/data/2.5/onecall")
    suspend fun getWeatherForCords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String
    ): Response<WeatherByCordsModel>

    @GET("data/2.5/air_pollution")
    suspend fun getAirPollutionForCords(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ) : Response<AirPollutionModel>

}