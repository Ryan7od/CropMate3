package com.example.cropmate.weather

class WeatherGetter {
/*    fun fetchWeatherData(cityName: String, apiKey: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(OpenWeatherMapApi::class.java)

        weatherApi.getCurrentWeatherData(cityName, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                response.body()?.let {
                    Log.d("WeatherData", "Date: ${Date()}")
                    Log.d("WeatherData", "Temperature: ${it.main.temp}")
                    Log.d("WeatherData", "Cloud Cover: ${it.clouds.all}")
                    Log.d("WeatherData", "Humidity: ${it.main.humidity}")
                    // Rainfall data if available
                    it.rain?.let { rain ->
                        Log.d("WeatherData", "Rainfall: ${rain.last3hours}")
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("WeatherData", "Error fetching weather data", t)
            }
        })
    }*/
}