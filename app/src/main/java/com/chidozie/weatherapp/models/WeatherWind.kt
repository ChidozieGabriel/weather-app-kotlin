package com.chidozie.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherWind(
    val id: Int,
    val speed: Double,
    val deg: Double
) : Parcelable
