package com.chidozie.weatherapp.models

import android.os.Parcelable
import android.util.Log
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class WeatherDetail(
  val id: Int,
  val weather: List<WeatherDescription>,
  @Embedded(prefix = "main_")
  val main: WeatherMain,
  @Embedded(prefix = "wind_")
  val wind: WeatherWind,
  val icon: String,
  val dt: Long
) : Parcelable {

  fun getDate(): Date {
    Log.e("GET DATE", dt.toString())
    return Date(dt * 1000)
  }
}
