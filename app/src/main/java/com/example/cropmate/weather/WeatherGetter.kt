package com.example.cropmate.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class WeatherGetter : AppCompatActivity() {
    private val API_KEY = "42cc4d60bd8c91b9ff79e2057a5f5f6a"
    private lateinit var weatherService: WeatherService

    fun getWeather(): Weather {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)


        return TODO()
    }


    /*private fun updateUI(weatherData: WeatherData) {
        findViewById<TextView>(R.id.textViewCity).text = weatherData.name
        findViewById<TextView>(R.id.textViewTemperature).text =
            "${weatherData.main.temp.toInt()}°C"
        val iconUrl = "https://openweathermap.org/img/w/${weatherData.weather[0].icon}.png"
        Glide.with(this)
            .load(iconUrl)
            .into(findViewById(R.id.imageViewWeatherIcon))
    }*/
}