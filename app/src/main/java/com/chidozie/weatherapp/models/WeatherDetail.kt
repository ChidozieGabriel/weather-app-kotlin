package com.chidozie.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDetail(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
