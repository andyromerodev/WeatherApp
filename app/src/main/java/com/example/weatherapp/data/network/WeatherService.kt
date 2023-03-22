package com.example.weatherapp.data.network

import com.example.weatherapp.core.RetrofitHelper
import com.example.weatherapp.data.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(private val api: WeatherApiClient) {

//    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getWeather(city, apiKey)
                if (response.isSuccessful) {
                    response.body()!!
                } else {
                    WeatherModel() // Devuelve una instancia de WeatherModel con valores predeterminados
                }
            } catch (e: Exception) {
                e.printStackTrace()
                WeatherModel() // Devuelve una instancia de WeatherModel con valores predeterminados
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
                    WeatherModel() // Devuelve una instancia de WeatherModel con valores predeterminados
                }
            } catch (e: Exception) {
                e.printStackTrace()
                WeatherModel() // Devuelve una instancia de WeatherModel con valores predeterminados
            }
        }
    }
}