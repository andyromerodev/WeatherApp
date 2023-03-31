package com.example.weatherapp.di

import com.example.weatherapp.core.RetrofitHelper
import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.network.WeatherApiClient
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherByCoordinates
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitHelper.getRetrofit()
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
        repository: WeatherRepository,
    ): GetWeatherUseCase {
        return GetWeatherUseCase(city, apiKey, repository)
    }

    @Provides
    @Named("getWeatherByCoordinates")
    fun provideGetWeatherByCoordinates(
        @Named("latitude") latitude: Double,
        @Named("longitude") longitude: Double,
        @Named("apiKey") apiKey: String,
        repository: WeatherRepository,
    ): GetWeatherByCoordinates {
        return GetWeatherByCoordinates(latitude, longitude, apiKey, repository)
    }

//    @Provides
//    @Singleton
//    fun provideWeatherRepository(
//        weatherApiService: WeatherService,
//        weatherDao: WeatherDao
//    ): WeatherRepository {
//        return WeatherRepositoryImpl(weatherApiService, weatherDao)
//    }

    @Provides
    @Named("city")
    fun provideCity(): String {
        return ""
    }

    @Provides
    @Named("apiKey")
    fun provideApiKey(): String {
        return ""
    }

    @Provides
    @Named("latitude")
    fun provideLatitude(): Double {
        return 0.0
    }

    @Provides
    @Named("longitude")
    fun provideLongitude(): Double {
        return 0.0
    }


}