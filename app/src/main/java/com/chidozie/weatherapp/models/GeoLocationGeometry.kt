package com.chidozie.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoLocationGeometry(
    val id: Int,
    val lat: Double,
    val lng: Double
) : Parcelable
