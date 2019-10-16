package com.chidozie.weatherapp.models

import android.os.Parcelable
import com.chidozie.weatherapp.view.toFixed1
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherMain(
    val id: Int,
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    val temp_min: Double,
    val temp_max: Double
) : Parcelable {

  fun getTempMax() {
    "max: ${temp_max.toFixed1()} C"
  }

  fun getTempMin() {
    "min: ${temp_min.toFixed1()} C"
  }
}
