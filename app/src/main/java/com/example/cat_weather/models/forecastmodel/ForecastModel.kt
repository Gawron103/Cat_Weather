package com.example.cat_weather.models.forecastmodel

import com.google.gson.annotations.SerializedName

data class ForecastModel(

    @SerializedName("city")
    val city: City,

//    val cnt: Int,

    @SerializedName("cod")
    val cod: String,

    @SerializedName("list")
    val list: List<WeatherForecast>,

//    val message: Int
)

data class WeatherForecast(

    @SerializedName("clouds")
    val clouds: Clouds,

//    @SerializedName("")
//    val dt: Long,

    @SerializedName("main")
    val main: Main,

    @SerializedName("sys")
    val Sys: Sys,

    @SerializedName("Rain")
    val rain: Rain,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("wind")
    val wind: Wind

)