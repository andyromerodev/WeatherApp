package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import javax.inject.Inject
import javax.inject.Named

class GetWeatherByCoordinates @Inject constructor(
    @Named("latitude") private var latitude: Double,
    @Named("longitude") private var longitude: Double,
    @Named("apiKey") private var apiKey: String,
    private val repository: WeatherRepository,
) {

    //private val repository = WeatherRepository()

    suspend operator fun invoke(latitude: Double, longitude: Double, apiKey: String): WeatherModel {

        this.latitude = latitude
        this.longitude = longitude
        this.apiKey = apiKey

        repository.getWeatherByCoordinates(this.latitude, this.longitude, this.apiKey)
        return WeatherProvider.resultWeatherProvider
    }
}