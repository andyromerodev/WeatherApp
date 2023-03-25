package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(private val api: WeatherApiClient) {

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getWeather(city, apiKey)
                if (response.isSuccessful) {
                    response.body()!!
                } else {
                    WeatherModel()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                WeatherModel()
            }
        }
    }

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String,
    ): WeatherModel {

        return withContext(Dispatchers.IO) {
            try {
                val response = api.getWeatherLatLong(latitude, longitude, apiKey)
                if (response.isSuccessful) {
                    response.body()!!
                } else {
                    WeatherModel()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                WeatherModel()
            }
        }
    }
}