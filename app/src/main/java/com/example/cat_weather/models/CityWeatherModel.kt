package com.example.cat_weather.models

import com.example.cat_weather.models.weathermodel.OpenWeatherCurrentWeatherModel

data class CityWeatherModel(
    val weatherModel: OpenWeatherCurrentWeatherModel,
    val cityPhotoUrl: String
)