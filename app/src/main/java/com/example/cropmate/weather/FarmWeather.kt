package com.example.cropmate.weather

import java.util.Date

data class FarmWeather(
    val date: Date,
    val highTemp: Int,
    val lowTemp: Int,
    val rainfall: Int,
    val humidity: Int,
    val cloudCover: Double,
)