package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.database.entities.toDatabase
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Named

class GetWeatherByCoordinates @Inject constructor(
    @Named("latitude") private var latitude: Double,
    @Named("longitude") private var longitude: Double,
    @Named("apiKey") private var apiKey: String,
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModelOnDomain {

        this.latitude = latitude
        this.longitude = longitude
        this.apiKey = apiKey

        val weather =
            repository.getWeatherFromApiByCoordinates(this.latitude, this.longitude, this.apiKey)
        repository.insertWeather(weather.toDatabase())
        return weather
    }
}