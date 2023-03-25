package com.example.weatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.database.entities.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table ORDER BY name DESC")
    suspend fun getAllWeather():List<WeatherEntity>

    @Query("SELECT * FROM weather_table WHERE name = :cityName")
    suspend fun getWeather(cityName: String): WeatherEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weathersList: WeatherEntity)

}