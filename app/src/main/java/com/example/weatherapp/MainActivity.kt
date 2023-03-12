package com.example.weatherapp

import android.content.pm.ApplicationInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applicationInfo: ApplicationInfo =
            packageManager.getApplicationInfoCompat(application.packageName, 0)

        val apiKey = applicationInfo.metaData.getString("API_KEY")
    }
}