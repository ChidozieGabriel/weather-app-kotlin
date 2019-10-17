package com.chidozie.weatherapp.view.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProviders
import com.chidozie.weatherapp.factory.ViewModelFactory
import com.chidozie.weatherapp.helpers.LiveDataTest
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
    this.viewModel =
      ViewModelProviders.of(this.activity, viewModelFactory)[HomeActivityViewModel::class.java]
  }

  @After
  fun stop() {
    mockServer.shutdown()
  }

  @Test
  fun getWeather() {
    mockServer.enqueue(
      MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(getFile("get-weather.json"))
    )

    assertThat(this.viewModel.weather, notNullValue())

    viewModel.fetchWeather(6.45, 3.39)
    val weather = LiveDataTest.getValue(viewModel.weather)

    assertThat(weather, notNullValue())
    assertEquals(6, weather.list.size)
  }

  private fun getFile(path: String): String {
    val uri = this.javaClass.classLoader!!.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
  }
}
