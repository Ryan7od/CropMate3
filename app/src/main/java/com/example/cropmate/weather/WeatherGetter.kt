package com.example.cropmate.weather

import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

class WeatherGetter : AppCompatActivity() {
    private val API_KEY = "42cc4d60bd8c91b9ff79e2057a5f5f6a"
    private lateinit var weatherService: WeatherService

    // CAN BE ""
    fun getCity(
        lat: Double,
        long: Double
    ): String {
        var cityName: String = "";

        // Max Start
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val geocoder: Geocoder = Geocoder(this, Locale.getDefault())

        // Max Added for getting weather


        try {
            val addresses = geocoder.getFromLocation(lat, long, 1) ?: return cityName
            if (addresses.size > 0) {
                for (a in addresses) {
                    if (a.locality != null && a.locality.isNotEmpty()) {
                        cityName = a.locality
                        break
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return cityName
    }

    fun getWeather(): WeatherData {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)




        GlobalScope.launch(Dispatchers.IO) {
            val weatherData = weatherService.getWeather(TODO(), API_KEY)
        }


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