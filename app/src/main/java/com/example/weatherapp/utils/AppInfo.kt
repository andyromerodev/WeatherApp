package com.example.weatherapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build

fun buildMinVersionT(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

fun PackageManager.getApplicationInfoCompat(packageName: String, flags: Int): ApplicationInfo {
    return if (buildMinVersionT()) {
        getApplicationInfo(packageName, PackageManager.ApplicationInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("deprecation")
        getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    }
}