package com.chidozie.weatherapp.api

import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {

  val code: Int
  val body: T?
  val error: Throwable?

  val isSuccessful: Boolean
    get() = code in 200..299


  constructor(error: Throwable) {
    code = 500
    body = null
    this.error = error
  }

  constructor(response: Response<T>) {
    code = response.code()
    if (response.isSuccessful) {
      body = response.body()
      error = null
    } else {
      error = IOException(response.errorBody()?.string() ?: response.message())
      body = null
    }
  }
}