package com.example.cat_weather.utils

object AirPollutionMapper {

    fun getAirQualityText(airPollutionValue: Int): String =
        when (airPollutionValue) {
            1 -> "Good"
            2 -> "Fair"
            3 -> "Moderate"
            4 -> "Poor"
            5 -> "Very Poor"
            else -> "Unknown"
        }

}