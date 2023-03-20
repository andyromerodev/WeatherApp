package com.example.weatherapp.data.network

import com.example.weatherapp.core.RetrofitHelper
import com.example.weatherapp.data.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(private val api:WeatherApiClient) {

//    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {

        return withContext(Dispatchers.IO) {
//            val response = retrofit.create(WeatherApiClient::class.java).getWeather(city, apiKey)
            val response = api.getWeather(city, apiKey)
            response.body()!!
        }
    }

    suspend fun getWeatherByCoordinates(latitude: Double, longitude: Double, apiKey: String): WeatherModel {

        return withContext(Dispatchers.IO) {
//            val response = retrofit.create(WeatherApiClient::class.java).getWeatherLatLong(latitude, longitude, apiKey)
            val response = api.getWeatherLatLong(latitude, longitude, apiKey)
            response.body()!!
        }
    }
}