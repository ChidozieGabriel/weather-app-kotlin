package com.chidozie.weatherapp.view.ui.home

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.chidozie.weatherapp.mock.MockObject
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

  @Rule
  @JvmField
  val activityTestRule = ActivityTestRule(HomeActivity::class.java, false, false)


  @Before
  fun setUp() {
    activityTestRule.launchActivity(Intent())
  }

  @After
  fun tearDown() {
  }


  @Test
  fun there_is_weather_view() {
    assertIsVisibleText(MockObject.exampleWeather.main.temp.toString())
  }

  private fun assertIsVisibleText(text: String) {
    //        onView(withText(text)).check(matches(isDisplayed()))
    Espresso.onView(ViewMatchers.withText(text))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }


}