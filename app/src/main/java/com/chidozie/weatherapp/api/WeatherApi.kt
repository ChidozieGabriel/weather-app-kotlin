package com.chidozie.weatherapp.api

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import com.chidozie.weatherapp.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather?q={city name},{country code}")
    public fun getWeateher(
        @SuppressWarnings("SameParameterValue")
        @Nullable @Query("countryCode") modified: String,
        @Query("city") apiKey: String
    ): LiveData<ApiResponse<WeatherResponse>>
}

/**
{
"coord": {"lon": -122.08,"lat": 37.39},
"weather": [
{
"id": 800,
"main": "Clear",
"description": "clear sky",
"icon": "01d"
}
],
"base": "stations",
"main": {
"temp": 296.71,
"pressure": 1013,
"humidity": 53,
"temp_min": 294.82,
"temp_max": 298.71
},
"visibility": 16093,
"wind": {
"speed": 1.5,
"deg": 350
},
"clouds": {
"all": 1
},
"dt": 1560350645,
"sys": {
"type": 1,
"id": 5122,
"message": 0.0139,
"country": "US",
"sunrise": 1560343627,
"sunset": 1560396563
},
"timezone": -25200,
"id": 420006353,
"name": "Mountain View",
"cod": 200
}
 */