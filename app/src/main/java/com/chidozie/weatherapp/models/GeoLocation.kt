package com.chidozie.weatherapp.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(primaryKeys = ["id"])
@Parcelize
data class GeoLocation(
    val id: Int,
    val results: List<GeoLocationDetail>
) : Parcelable
