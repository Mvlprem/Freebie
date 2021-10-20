package com.mvlprem.freebie

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class FreebieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("BTN_STATE", MODE_PRIVATE)
        val value = sharedPreferences.all
        when {
            value["btn_default"] == true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            value["btn_light"] == true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            value["btn_dark"] == true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}