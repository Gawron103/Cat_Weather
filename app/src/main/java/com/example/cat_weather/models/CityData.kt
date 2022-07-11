package com.example.cat_weather.models

import com.example.cat_weather.models.airpollutionmodel.AirPollutionModel
import com.example.cat_weather.models.forecastmodel.WeatherForecast
import com.example.cat_weather.models.weatherbynamemodel.WeatherByNameModel

data class CityData(

    val weatherModel: WeatherByNameModel,

    val forecastModel: Map<String, List<WeatherForecast>>,

    val airPollutionModel: AirPollutionModel,

    val cityImg: String

)