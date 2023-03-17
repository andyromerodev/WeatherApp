package com.example.weatherapp.data.network

import com.example.weatherapp.core.RetrofitHelper
import com.example.weatherapp.data.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(WeatherApiClient::class.java).getWeather(city, apiKey)
            response.body()!!
        }
    }

    suspend fun getWeatherByLatLong(city: String, apiKey: String): WeatherModel {

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(WeatherApiClient::class.java).getWeatherLatLong(city, apiKey)
            response.body()!!
        }
    }
}