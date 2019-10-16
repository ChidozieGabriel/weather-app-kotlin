package com.chidozie.weatherapp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.chidozie.weatherapp.helpers.LiveDataTest.getValue
import com.chidozie.weatherapp.helpers.MockObject
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {

  private lateinit var database: AppDatabase
  private lateinit var weatherDao: WeatherDao

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun createDB() {
    val context = InstrumentationRegistry.getTargetContext()

    database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    weatherDao = database.weatherDao()
    weatherDao.insertWeather(MockObject.weather)
  }

  @After
  fun closeDB() {
    database.close()
  }

  @Test
  fun getCharactersOrderedByPage() {
    val weatherList = getValue(weatherDao.getWeathers())
    Assert.assertThat(weatherList.size, Matchers.equalTo(1))
    Assert.assertThat(weatherList[0], Matchers.equalTo(MockObject.weather))
  }
}

