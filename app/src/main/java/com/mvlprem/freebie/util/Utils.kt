package com.mvlprem.freebie.util

import androidx.appcompat.app.AppCompatDelegate

/**
 * util function to change theme
 * @param value user selection from DataStore
 */
fun userPreferredTheme(value: Int) {
    AppCompatDelegate.setDefaultNightMode(
        when (value) {
            0 -> AppCompatDelegate.MODE_NIGHT_NO
            1 -> AppCompatDelegate.MODE_NIGHT_YES
            else -> {
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        }
    )
}