package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherService,
    private val weatherDao: WeatherDao
) {

    //private val api = WeatherService()

//    suspend fun getAllWeatherFromDatabase(): List<WeatherModelOnDomain>{}

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {

        Log.d("TAGgetWeatherByCity", city)
        Log.d("TAGgetWeatherByCity", apiKey)

        val response = api.getWeatherByCity(city, apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModel {
        val response = api.getWeatherByCoordinates(latitude, longitude, apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

}