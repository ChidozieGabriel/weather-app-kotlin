package com.chidozie.weatherapp.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class UtilsTest {

  @Test
  fun return_day_of_week() {
    assertEquals("MONDAY", Utils.getDay(0))
    assertEquals("SUNDAY", Utils.getDay(6))
  }

  @Test
  fun throw_error() {
    assertFailsWith(AssertionError::class) { Utils.getDay(7) }
  }
}