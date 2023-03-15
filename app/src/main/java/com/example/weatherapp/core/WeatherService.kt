package com.example.weatherapp.core

import com.example.weatherapp.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel{

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(WeatherApiClient::class.java).getWeather(city, apiKey)
            response.body()!!
        }
    }
}