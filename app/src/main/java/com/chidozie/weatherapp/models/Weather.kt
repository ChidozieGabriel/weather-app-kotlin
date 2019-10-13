package com.chidozie.weatherapp.models

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Weather(
    val id: Int,
    val city: String,
    val temperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val date: Long,
    val humidity: Int,
    val windSpeed: Int
)