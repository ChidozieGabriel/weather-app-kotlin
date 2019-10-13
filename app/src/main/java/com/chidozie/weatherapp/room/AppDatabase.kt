package com.chidozie.weatherapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [(Character::class)],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}