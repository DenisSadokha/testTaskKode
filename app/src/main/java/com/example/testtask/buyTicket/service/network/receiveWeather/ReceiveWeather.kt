package com.example.testtask.buyTicket.service.network.receiveWeather

import com.example.testtask.buyTicket.data.WeatherData
import com.example.testtask.buyTicket.main.findTicket.PBuyTicket
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ReceiveWeather(val city: String, val presenter: PBuyTicket) {
    val url = "http://api.openweathermap.org/"
    val appid = "24b1e52fe2d7190bd48ca3734652bce6"
     fun makeRequest() {
        println(city)
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val link = retrofit.create(LinkForm::class.java)
        val call = link.call(city = "$city,ru", appid = appid, mode = "json")
        println(call.request().url())
        call.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                val list = ArrayList<WeatherData>()
                response.body()?.get("list")?.asJsonArray?.forEach { el ->
                    run {
                        list.add(
                            WeatherData(
                                temp = el.asJsonObject.get("main").asJsonObject.get("temp")
                                    .toString(),
                                minTemp = el.asJsonObject.get("main").asJsonObject.get("temp_min")
                                    .toString(),
                                maxTemp = el.asJsonObject.get("main").asJsonObject.get("temp_max")
                                    .toString(),
                                city = city,
                                feelsLike = el.asJsonObject.get("main").asJsonObject.get("feels_like")
                                    .toString(),
                                speedWind = el.asJsonObject.get("wind").asJsonObject.get("speed")
                                    .toString(),
                                weather = el.asJsonObject.get("weather").asJsonArray.get(0).asJsonObject.get(
                                    "main"
                                )
                                    .toString()
                            )
                        )
                    }
                }

                presenter.receiveSuccess(list)
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                println("failure" + t.printStackTrace())
            }
        })

    }

}