package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.GetAllWeatherUseCase
import com.example.weatherapp.domain.usecase.GetWeatherByCoordinates
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @Named("getWeatherUseCase") private val resultGetWeatherUseCase: GetWeatherUseCase,
    @Named("getWeatherByCoordinates") private val resultGetWeatherByCoordinates: GetWeatherByCoordinates,
    @Named("getAllWeatherUseCase") private val resultGetAllWeatherUseCase: GetAllWeatherUseCase,
) : ViewModel() {

    private val _weatherModel = MutableLiveData<WeatherModelOnDomain>()
    val weatherModel: LiveData<WeatherModelOnDomain> = _weatherModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _viewButton = MutableLiveData<Boolean>()
    val viewButton: LiveData<Boolean> = _viewButton

    private val _getListWeather = MutableLiveData<List<WeatherModelOnDomain>>()
    val getListWeather: LiveData<List<WeatherModelOnDomain>> = _getListWeather

    private val _cityViewModel: MutableLiveData<String> = MutableLiveData()
    private val cityViewModel: LiveData<String> = _cityViewModel

    private val _apiKeyViewModel: MutableLiveData<String> = MutableLiveData()
    private val apiKeyViewModel: LiveData<String> = _apiKeyViewModel

    private val _latitudeViewModel: MutableLiveData<Double> = MutableLiveData()
    private val latitudeViewModel: LiveData<Double> = _latitudeViewModel

    private val _longitudeViewModel: MutableLiveData<Double> = MutableLiveData()
    private val longitudeViewModel: LiveData<Double> = _longitudeViewModel

    fun getListWeather(): List<WeatherModelOnDomain>? {
        return _getListWeather.value
    }

    fun updateCityViewModel(city: String) {
        _cityViewModel.value = city
    }

    fun updateApiKeyViewModel(apiKey: String) {
        _apiKeyViewModel.value = apiKey
    }

    fun updateLatitudeViewModel(latitude: Double) {
        _latitudeViewModel.value = latitude
    }

    fun updateLongitudeViewModel(longitude: Double) {
        _longitudeViewModel.value = longitude
    }

    fun getWeatherByCity() {
        viewModelScope.launch {
            _viewButton.postValue(false)
            _isLoading.postValue(true)

            val currentWeather =
                resultGetWeatherUseCase(
                    cityViewModel.value.toString(),
                    apiKeyViewModel.value.toString()
                )

            _weatherModel.postValue(currentWeather)

            if (!resultGetWeatherUseCase.equals(0)) {
                _isLoading.postValue(false)
                _viewButton.postValue(true)
            }
        }
    }

    fun getWeatherByCoordinates() {
        viewModelScope.launch {
            _viewButton.postValue(false)
            _isLoading.postValue(true)

            val currentWeather = resultGetWeatherByCoordinates(
                latitudeViewModel.value?.toDouble() ?: 0.0,
                longitudeViewModel.value?.toDouble() ?: 0.0,
                apiKeyViewModel.value.toString()
            )

            _weatherModel.postValue(currentWeather)

            if (!currentWeather.equals(0)) {
                _isLoading.postValue(false)
                _viewButton.postValue(true)
            }
        }
    }

    fun getAllWeather() {
        viewModelScope.launch {
            val currentWeather = resultGetAllWeatherUseCase()

            _getListWeather.postValue(currentWeather)

        }
    }
}


