package com.example.weatherapp.di

import android.os.Build
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.network.WeatherApiClient
import com.example.weatherapp.domain.GetWeatherUseCase
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideWeatherApiClient(retrofit: Retrofit): WeatherApiClient {
        return retrofit.create(WeatherApiClient::class.java)
    }


    @Provides
    @Named("getWeatherUseCase")
    fun provideGetWeatherUseCase(
        @Named("city") city: String,
        @Named("apiKey") apiKey: String,
        repository: WeatherRepository
    ): GetWeatherUseCase {
        return GetWeatherUseCase(city, apiKey, repository)
    }

    @Provides
    @Named("city")
    fun provideCity(): String {
        return ""
    }

    @Provides
    @Named("apiKey")
    fun provideApiKey(): String {
        // Devuelve el valor de la propiedad "apiKey" del archivo de configuración
        return ""
    }


}