package com.weatherveil.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>

    @GET("weather")
    suspend fun getCurrentWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("cnt") count: Int = 40
    ): Response<ForecastResponse>

    @GET("forecast")
    suspend fun getForecastByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("cnt") count: Int = 40
    ): Response<ForecastResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val instance: WeatherApiService by lazy {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
        retrofit.create(WeatherApiService::class.java)
    }
}
