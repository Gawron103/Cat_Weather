package com.example.cat_weather.utils

import android.graphics.Color

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

    fun getAirQualityDonutColor(airPollutionValue: Int): Int {
        val color = when (airPollutionValue) {
            1 -> "#80ff80"
            2 -> "#99cc66"
            3 -> "#b98c46"
            4 -> "#d3592d"
            5 -> "#ff0000"
            else -> "#ffffff"
        }

        return Color.parseColor(color)
    }

}