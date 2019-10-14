package com.chidozie.weatherapp.models

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoLocationDetail(
    val id: Int,
    @Embedded(prefix = "components_")
    val components: GeoLocationComponents,
    @Embedded(prefix = "geometry_")
    val geometry: GeoLocationGeometry
) : Parcelable
