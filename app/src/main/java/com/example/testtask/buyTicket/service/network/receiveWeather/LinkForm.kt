package com.example.testtask.buyTicket.service.network.receiveWeather

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LinkForm {
@GET("data/2.5/forecast")
     fun call(
        @Query("q") city: String,
        @Query("mode") mode: String,
        @Query("appid") appid: String
    ): Call<JsonObject?>
}