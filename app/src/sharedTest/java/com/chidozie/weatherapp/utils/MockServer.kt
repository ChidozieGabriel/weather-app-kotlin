package com.chidozie.weatherapp.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

object MockServer {

  @Throws(IOException::class)
  fun enqueueResponse(
    server: MockWebServer,
    fileName: String,
    headers: Map<String, String> = emptyMap()
  ) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = Okio.buffer(Okio.source(inputStream))
    val mockResponse = MockResponse()
    for ((key, value) in headers) {
      mockResponse.addHeader(key, value)
    }
    server.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
  }

  fun getFile(path: String): String {
    val uri = this.javaClass.classLoader!!.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
  }
}