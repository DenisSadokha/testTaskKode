package com.example.testtask.buyTicket.data

import java.io.Serializable

data class WeatherData(
    val temp: String,
    val minTemp: String,
    val maxTemp: String,
    val feelsLike: String,
    val speedWind: String,
    val weather: String,
    val city: String
) : Serializable