package com.example.cat_weather.models.airpollutionmodel

import com.google.gson.annotations.SerializedName

data class Components(

    @SerializedName("co")
    val co: Double,

    @SerializedName("no")
    val no: Double,

    @SerializedName("no2")
    val no2: Double,

    @SerializedName("o3")
    val o3: Double,

    @SerializedName("so2")
    val so2: Double,

    @SerializedName("pm2_5")
    val pm2_5: Double,

    @SerializedName("pm10")
    val pm10: Double,

    @SerializedName("nh3")
    val nh3: Double

)