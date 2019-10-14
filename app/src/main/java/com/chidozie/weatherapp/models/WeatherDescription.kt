package com.chidozie.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
