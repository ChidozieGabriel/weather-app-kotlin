package com.chidozie.weatherapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chidozie.weatherapp.models.Weather

@Database(
    entities = [(Weather::class)],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}