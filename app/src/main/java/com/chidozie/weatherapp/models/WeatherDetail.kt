package com.chidozie.weatherapp.models

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDetail(
    val id: Int,
    val weather: List<WeatherDescription>,
    @Embedded(prefix = "main_")
    val main: WeatherMain,
    @Embedded(prefix = "wind_")
    val wind: WeatherWind,
    val icon: String
) : Parcelable
