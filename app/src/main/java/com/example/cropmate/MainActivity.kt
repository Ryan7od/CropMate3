package com.example.cropmate

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.cropmate.databinding.ActivityMainBinding
import com.example.cropmate.fieldManagement.FieldManagementActivity
import com.example.cropmate.weather.FarmWeather
import com.example.cropmate.weather.WeatherData
import com.example.cropmate.weather.WeatherService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val API_KEY = "42cc4d60bd8c91b9ff79e2057a5f5f6a"

    private lateinit var weatherService: WeatherService

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setListeners()

        setWeather()
    }

    fun setListeners() {

        // SETTINGS
        val settingsButton: ImageButton = findViewById(R.id.SettingsButton)
        settingsButton.setOnClickListener {
            /*val intent = Intent(this, PAGE::class.java)
            startActivity(intent)
            finish()*/
            // TODO: Add settings
        }

        // PROFILE
        val profileButton: ImageButton = findViewById(R.id.ProfileButton)
        profileButton.setOnClickListener {
            /*val intent = Intent(this, PAGE::class.java)
            startActivity(intent)
            finish()*/
            // TODO: Profile
        }

        // YOUR FARM
        val yourFarmButton: Button = findViewById(R.id.YourFarmButton)
        yourFarmButton.setOnClickListener {
            val intent = Intent(this, FieldManagementActivity::class.java)
            startActivity(intent)
            finish()
        }

        // TO DO LIST
        val toDoListButton: Button = findViewById(R.id.ToDoListButton)
        toDoListButton.setOnClickListener {
            val intent = Intent(this, ToDo::class.java)
            startActivity(intent)
            finish()
        }

        // INVENTORY
        val inventoryButton: Button = findViewById(R.id.InventoryButton)
        inventoryButton.setOnClickListener {
            val intent = Intent(this, InventoryPage::class.java)
            startActivity(intent)
            finish()
        }

        // FINANCIAL MANAGEMENT
        val financialManagementButton: Button = findViewById(R.id.FinancialManagementButton)
        financialManagementButton.setOnClickListener {
            /*val intent = Intent(this, PAGE::class.java)
            startActivity(intent)
            finish()*/
            // TODO: Add Financial Managment Page
        }
    }

    fun setWeather() {
        val weather: FarmWeather =
            FarmWeather(
            date = Date(124, 2, 10),
            location = "London",
            lowTemp = 7,
            highTemp = 9,
            rainfall = 6,
            humidity = 89,
            cloudCover = 7,
        ) // Get weather

        val dateText: TextView = findViewById(R.id.Date)
        dateText.text = SimpleDateFormat("dd/MM/yyyy").format(weather.date)

        val locationText: TextView = findViewById(R.id.Location)
        locationText.text = weather.location

        val tempText: TextView = findViewById(R.id.Temperature)
        tempText.text = "L: ${weather.lowTemp}°C\nH: ${weather.highTemp}°C"

        val rainText: TextView = findViewById(R.id.Rainfall)
        rainText.text = "${weather.rainfall}mm"

        val humidityText: TextView = findViewById(R.id.Humidity)
        humidityText.text = "${weather.humidity}%"

        val cloudText: TextView = findViewById(R.id.CloudCover)
        cloudText.text = "${weather.cloudCover} Oktas"
    }

    fun getLongLat(): Pair<Double, Double>? = getLongLat(0)

    fun getLongLat(depth: Int): Pair<Double, Double>? {
        if (depth > 1) return null

        var latitude: Double? = null
        var longitude: Double? = null

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.getLastLocation()
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        latitude = location.latitude
                        longitude = location.longitude
                    }
                }

            Log.i("Location Allowed", "Location has been allowed: lat ($latitude), long ($longitude)")
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )

            Log.i("Fine Location Error","No access to fine location")
            return getLongLat(depth + 1)
        }
        if (latitude == null || longitude == null) Log.i("LongLat Null","Long or Lat Null")
        return if (latitude == null || longitude == null) null
        else Pair(latitude!!, longitude!!) // Safe
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                } else {
                    // Permission Denied
                    Log.e("Location permission denied", "Location permission denied")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun getCity(
        lat: Double,
        long: Double
    ): String {
        var cityName: String = ""

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

    @OptIn(DelicateCoroutinesApi::class)
    fun getWeather(): FarmWeather {
        val latLong: Pair<Double, Double>? = getLongLat()
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/3.0/onecall/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)


        if (latLong == null) {
            return FarmWeather(
                date = Date(1900, 1, 1),
                location = "NULL",
                lowTemp = 0,
                highTemp = 0,
                rainfall = 0,
                humidity = 0,
                cloudCover = 0,
            )
        }

        var weatherData: WeatherData? = null
        GlobalScope.launch(Dispatchers.IO) {
            weatherData = weatherService.getWeather(latLong.first.toString(), latLong.second.toString(), API_KEY)
        }
        return TODO()
    }
}