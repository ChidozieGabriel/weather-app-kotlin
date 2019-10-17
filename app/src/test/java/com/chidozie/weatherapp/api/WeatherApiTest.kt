package com.chidozie.weatherapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chidozie.weatherapp.helpers.LiveDataTest
import com.chidozie.weatherapp.utils.Utils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Okio
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
class WeatherApiTest {
  val lat = 6.45
  val lon = 3.39
  val cnt = 6

  @Rule
  @JvmField
  val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  private lateinit var service: WeatherApi
  private lateinit var mockWebServer: MockWebServer

  @Throws(IOException::class)
  @Before
  fun createService() {
    mockWebServer = MockWebServer()
    service = Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(LiveDataCallAdapterFactory())
      .build()
      .create(WeatherApi::class.java)
  }

  @Throws(IOException::class)
  @After
  fun stopService() {
    mockWebServer.shutdown()
  }

  @Test
  fun getWeather() {
    enqueueResponse("get-weather.json")
    val weather = LiveDataTest.getValue(
      service.getWeather(
        latitude = lat,
        longitude = lon,
        limit = cnt
      )
    ).body


    val request: RecordedRequest = mockWebServer.takeRequest()
    MatcherAssert.assertThat(
      request.path,
      CoreMatchers.`is`("/data/2.5/forecast?lon=$lon&lat=$lat&APPID=${Utils.WEATHER_API_KEY}&cnt=$cnt")
    )
    MatcherAssert.assertThat(weather, CoreMatchers.notNullValue())
    MatcherAssert.assertThat(weather?.city?.id, CoreMatchers.`is`(2332459))
    MatcherAssert.assertThat(weather?.city?.name, CoreMatchers.`is`("Lagos"))
    MatcherAssert.assertThat(weather?.list?.size, CoreMatchers.`is`(cnt))
  }

  @Throws(IOException::class)
  private fun enqueueResponse(fileName: String) {
    enqueueResponse(fileName, emptyMap())
  }

  @Throws(IOException::class)
  private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = Okio.buffer(Okio.source(inputStream))
    val mockResponse = MockResponse()
    for ((key, value) in headers) {
      mockResponse.addHeader(key, value)
    }
    mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
  }
}