package com.example.cropmate.weather

import java.util.Date

data class FarmWeather(
    val date: Date,
    val location: String,
    val highTemp: Int,
    val lowTemp: Int,
    val rainfall: Int,
    val humidity: Int,
    val cloudCover: Int,
)