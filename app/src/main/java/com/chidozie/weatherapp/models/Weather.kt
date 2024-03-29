package com.chidozie.weatherapp.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(primaryKeys = ["id"])
@Parcelize
data class Weather(
    val id: Int,
    @Embedded(prefix = "city_")
    val city: WeatherCity,
    val list: List<WeatherDetail>
) : Parcelable
