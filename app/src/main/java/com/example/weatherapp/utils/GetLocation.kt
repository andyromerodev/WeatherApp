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

    //lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val coord = mutableMapOf<String, String>()

    private var mLocation: android.location.Location? = null

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context): Task<Location>? {

        val permission = GetPermission()
        permission.checkPermission(context)

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            val location =
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                    Log.d("LOCATIONGPS", location?.latitude.toString())
                    Log.d("LOCATIONGPS", location?.longitude.toString())
                }

            return location
        } catch (e: SecurityException) {
            Log.e("TAG1", e.toString())
        }
        return null
    }


}


//        location.addOnSuccessListener { location ->
//
//            mLocation = location
//
//            Log.d("LOCATIONGPS", location.latitude.toString())
//            Log.d("LOCATIONGPS", location.longitude.toString())
//
//            val latitude = location.latitude.toString()
//            val longitude = location.longitude.toString()
//
//            getCoord(latitude, longitude)


//private fun getCoord(latitude: String, longitude: String) {
//    val latitudeR: String = latitude
//    val longitudeR: String = longitude
//    coord["latitude"] = latitudeR
//    coord["longitude"] = longitudeR
//    Log.d("LOCATIONRESULT", coord["latitude"].toString())
//
//}
//
//fun getCoordenadas(): Map<String, String> {
//    Log.d("LOCATIONRESULT", coord["latitude"].toString())
//    return coord
//}
