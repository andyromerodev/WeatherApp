package com.example.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
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
                }

            return location
        } catch (e: SecurityException) {
            Log.e("TAG1", e.toString())
        }
        return null
    }


}