package com.chidozie.weatherapp.view.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProviders
import com.chidozie.weatherapp.factory.ViewModelFactory
import com.chidozie.weatherapp.helpers.LiveDataTest
import com.chidozie.weatherapp.modules.DaggerTestAppComponent
import com.chidozie.weatherapp.utils.Utils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.io.File
import java.net.HttpURLConnection
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class HomeActivityViewModelTest {
  lateinit var mockServer: MockWebServer
  private lateinit var activity: AppCompatActivity
  private lateinit var viewModel: HomeActivityViewModel
  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  @Rule
  @JvmField
  val rule = InstantTaskExecutorRule()

  @Before
  fun setUp() {
    mockServer = MockWebServer()
    mockServer.start()
    this.activity = Robolectric.setupActivity(AppCompatActivity::class.java)
    configureDI()
    this.viewModel =
      ViewModelProviders.of(this.activity, viewModelFactory)[HomeActivityViewModel::class.java]
  }

  @After
  fun stop() {
    mockServer.shutdown()
  }

  private fun configureDI() {
    DaggerTestAppComponent.builder()
      .application(this.activity.application)
      .baseGeoLocationUrl(Utils.GEO_LOCATION_URL)
      .baseWeatherUrl(mockServer.url("/").toString())
      .build()
      .inject(this)
  }

  @Test
  fun getWeather() {
    mockServer.enqueue(
      MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(getFile("api-response/get-weather.json"))
    )

    val x = viewModel.fetchWeather(6.45, 3.39)
    val apiResponse = LiveDataTest.getValue(x)
    val weather = apiResponse.body

    assertThat(apiResponse, notNullValue())
    assertTrue { apiResponse.isSuccessful }
    assertEquals(6, weather?.list?.size)
  }

  private fun getFile(path: String): String {
    val uri = this.javaClass.classLoader!!.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
  }
}
