package com.example.cropmate.weather

import java.util.Date

class Weather(
    val date: Date,
    val temperature: Double, // Assuming temperature is in Celsius
    val cloudCover: Int, // Percentage of cloud cover
    val rainfall: Double?, // Rainfall in mm (nullable, as it might not always be available)
    val humidity: Int // Humidity in percentage
) {

}