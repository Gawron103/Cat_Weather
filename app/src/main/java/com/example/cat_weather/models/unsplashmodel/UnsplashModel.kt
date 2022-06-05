package com.example.cat_weather.models.unsplashmodel

data class UnsplashModel(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)