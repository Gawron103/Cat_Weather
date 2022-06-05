package com.example.cat_weather.utils

import kotlin.math.roundToInt

object TemperatureConverter {

    fun convertKelvinToCelsius(temp: Double): Int {
        return (temp - 273.15).roundToInt()
    }

}