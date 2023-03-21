package com.example.weatherapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.app.ComponentActivity

class GetPermission(activity: AppCompatActivity, private val permission: String) {

    private val permissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                when {
                    isGranted -> Toast.makeText(activity, "PERMISO GPS OTORGADO", Toast.LENGTH_LONG)
                        .show()
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ->
                        Toast.makeText(activity,
                            "ESTE PERMISO ES NECESARIO PARA OBTENER LAS COORDENADAS",
                            Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(activity, "PERMISO GPS DENEGADO", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    fun runPermissionLocation() {
        permissionLauncher.launch(permission)
    }
}