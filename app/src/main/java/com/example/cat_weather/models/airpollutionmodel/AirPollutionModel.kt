package com.example.cat_weather.models.airpollutionmodel

import com.google.gson.annotations.SerializedName

data class AirPollutionModel(

    @SerializedName("list")
    val list: List<PollutionData>

)

data class PollutionData(

    @SerializedName("main")
    val main: Main,

    @SerializedName("components")
    val components: Components,

)