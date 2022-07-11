package com.example.cat_weather.models.forecastmodel

import com.google.gson.annotations.SerializedName

data class ForecastModel(

    @SerializedName("city")
    val city: City,

    @SerializedName("cod")
    val cod: String,

    @SerializedName("list")
    val list: List<WeatherForecast>,

)

data class WeatherForecast(

    @SerializedName("clouds")
    val clouds: Clouds,

    @SerializedName("dt")
    val dt: Long,

    @SerializedName("main")
    val main: Main,

    @SerializedName("sys")
    val Sys: Sys,

    @SerializedName("Rain")
    val rain: Rain,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("wind")
    val wind: Wind,

    @SerializedName("dt_txt")
    val dt_txt: String

)