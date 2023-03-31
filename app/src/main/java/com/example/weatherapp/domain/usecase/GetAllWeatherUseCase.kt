package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetAllWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend operator fun invoke(): List<WeatherModelOnDomain> {

        return repository.getAllWeather()
        
    }
}