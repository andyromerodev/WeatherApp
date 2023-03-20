package com.example.weatherapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.viewModels
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class GetLocation {

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context): Task<Location>? {

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            val location =
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                    val latitude = location?.latitude ?: 0.0
                    val longitude = location?.longitude ?: 0.0
                    Log.d("LOCATIONGPS", "Latitud: $latitude, Longitud: $longitude")
                }

            return location
        } catch (e: SecurityException) {
            Log.e("TAG1", e.toString())
        }
        return null
    }


}