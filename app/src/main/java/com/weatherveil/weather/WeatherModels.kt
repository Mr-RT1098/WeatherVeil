package com.weatherveil.weather

import com.google.gson.annotations.SerializedName

// Current weather response model
data class WeatherResponse(
    val name: String,
    val main: MainWeather,
    val weather: List<WeatherDescription>,
    val wind: Wind,
    val sys: Sys,
    val visibility: Int,
    val dt: Long
)

data class MainWeather(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
)

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

// 5-day forecast response
data class ForecastResponse(
    val list: List<ForecastItem>,
    val city: CityInfo
)

data class ForecastItem(
    val dt: Long,
    val main: MainWeather,
    val weather: List<WeatherDescription>,
    val wind: Wind,
    @SerializedName("dt_txt") val dateText: String
)

data class CityInfo(
    val name: String,
    val country: String
)
