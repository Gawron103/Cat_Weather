package com.example.cat_weather.models

import com.example.cat_weather.models.forecastmodel.ForecastModel
import com.example.cat_weather.models.weatherbynamemodel.WeatherByNameModel

data class CityData(

    val weatherModel: WeatherByNameModel,

    val forecastModel: ForecastModel,

    val cityImg: String

)