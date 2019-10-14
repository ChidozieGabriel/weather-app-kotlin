package com.chidozie.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chidozie.geoLocationapp.database.GeoLocationDao
import com.chidozie.weatherapp.models.GeoLocation
import com.chidozie.weatherapp.models.Weather

@Database(
    entities = [(Weather::class), (GeoLocation::class)],
    version = 4
)
@TypeConverters(AppTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

  abstract fun weatherDao(): WeatherDao

  abstract fun geoLocationDao(): GeoLocationDao
}