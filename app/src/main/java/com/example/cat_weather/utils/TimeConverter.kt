package com.example.cat_weather.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeConverter {

    fun convertTime(timestamp: Int): String {
        return SimpleDateFormat("HH:mm aa").format(Date(timestamp.toLong() * 1000))
    }

}