package com.example.cat_weather.models.weathermodel

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)