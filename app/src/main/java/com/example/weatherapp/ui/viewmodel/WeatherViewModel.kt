package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.domain.GetWeatherByCoordinates
import com.example.weatherapp.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private var result: GetWeatherUseCase) : ViewModel() {

    val weatherModel = MutableLiveData<WeatherModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun getWeatherByCity(city: String, apiKey: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            result = GetWeatherUseCase(city, apiKey, repository = WeatherRepository())

            result.invoke()

            //val result = GetWeatherUseCase(city, apiKey).invoke()

            val currentWeather = WeatherProvider.resultWeatherProvider

            weatherModel.postValue(currentWeather)

            if (!result.equals(0)) {
                isLoading.postValue(false)
            }
        }
    }

    fun getWeatherByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = GetWeatherByCoordinates(latitude, longitude, apiKey).invoke()

            val currentWeather = WeatherProvider.resultWeatherProvider

            weatherModel.postValue(currentWeather)

            if (!result.equals(0)) {
                isLoading.postValue(false)
            }
        }
    }

}


