package com.chidozie.weatherapp.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(primaryKeys = ["id"])
@Parcelize
data class WeatherResponse(
    val id: Int,
    val weather: List<WeatherDetail>,
    @Embedded(prefix = "main_")
    val main: WeatherMain,
    @Embedded(prefix = "wind_")
    val wind: WeatherWind,
    val icon: String
) : Parcelable
