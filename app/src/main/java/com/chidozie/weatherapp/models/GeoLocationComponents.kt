package com.chidozie.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoLocationComponents(
    val id: Int,
    val city: String,
    val country: String,
    val state: String
) : Parcelable
