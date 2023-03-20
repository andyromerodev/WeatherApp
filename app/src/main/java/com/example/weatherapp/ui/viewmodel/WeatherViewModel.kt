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
import javax.inject.Named

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @Named("getWeatherUseCase") private val resultGetWeatherUseCase: GetWeatherUseCase,
    @Named("getWeatherByCoordinates") private val resultGetWeatherByCoordinates: GetWeatherByCoordinates,
    private val repository: WeatherRepository,
) : ViewModel() {

    val weatherModel = MutableLiveData<WeatherModel>()
    val isLoading = MutableLiveData<Boolean>()
    val cityViewModel: MutableLiveData<String> = MutableLiveData()
    val apiKeyViewModel: MutableLiveData<String> = MutableLiveData()
    val latitudeViewModel: MutableLiveData<Double> = MutableLiveData()
    val longitudeViewModel: MutableLiveData<Double> = MutableLiveData()


//    val apiKeyViewModel = MutableLiveData<String>()

    fun getWeatherByCity() {
        viewModelScope.launch {
            isLoading.postValue(true)

            Log.d("CVM", cityViewModel.value.toString())
            Log.d("CVM", apiKeyViewModel.value.toString())

            val currentWeather =
                resultGetWeatherUseCase(cityViewModel.value.toString(),
                    apiKeyViewModel.value.toString())

            weatherModel.postValue(currentWeather)

            if (!resultGetWeatherUseCase.equals(0)) {
                isLoading.postValue(false)
            }
        }
    }

    fun getWeatherByCoordinates() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val currentWeather = resultGetWeatherByCoordinates(
                latitudeViewModel.value?.toDouble() ?: 0.0,
                longitudeViewModel.value?.toDouble() ?: 0.0,
                apiKeyViewModel.value.toString())

//            val currentWeather = WeatherProvider.resultWeatherProvider

            weatherModel.postValue(currentWeather)

            if (!currentWeather.equals(0)) {
                isLoading.postValue(false)
            }
        }
    }
}


