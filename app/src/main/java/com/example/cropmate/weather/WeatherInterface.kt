package com.example.cropmate.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("appid") apiKey: String,
    ): WeatherData
}