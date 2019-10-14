package com.chidozie.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherCity(
    val id: Int,
    val name: String
) : Parcelable
