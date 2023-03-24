package com.example.weatherapp.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.database.entities.WeatherEntity

interface WeatherDao {

    @Query("SELECT * FROM weather_table ORDER BY name DESC")
    suspend fun getAllWeather():List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weathersList: List<WeatherEntity>)

}