package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val WEATHER_DATABASE_NAME = "weather_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WeatherDatabase::class.java, WEATHER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideWeatherDao(db: WeatherDatabase) = db.getWeatherDao()

}