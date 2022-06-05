package com.example.cat_weather.apis

import com.example.cat_weather.BuildConfig
import com.example.cat_weather.models.unsplashmodel.UnsplashModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Accept-Version: v1", "Authorization: Client-ID ${BuildConfig.UNSPLASH_KEY}")
    @GET("/search/photos")
    suspend fun getPhotoForCity(
        @Query("query") query: String,
        @Query("orientation") orientation: String
    ): Response<UnsplashModel>

}