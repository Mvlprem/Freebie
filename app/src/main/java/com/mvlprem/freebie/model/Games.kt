package com.mvlprem.freebie.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Data class with properties that match the JSON response fields
 * it's Parcelable so we can share between fragments
 */
@Parcelize
data class Games(
    val id: Int,
    val title: String,
    val worth: String,
    val image: String,
    val description: String,
    val instructions: String,
    val type: String,
    @Json(name = "open_giveaway")
    val openGiveaway: String
) : Parcelable
