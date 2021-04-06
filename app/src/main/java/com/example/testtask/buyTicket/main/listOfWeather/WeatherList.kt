package com.example.testtask.buyTicket.main.listOfWeather

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.buyTicket.data.WeatherData
import com.example.testtask.buyTicket.main.listOfWeather.recycler.Adapter

private var weatherList: ArrayList<WeatherData> = ArrayList()
lateinit var recyclerView: RecyclerView
lateinit var toolbar: Toolbar
lateinit var tvDirection: TextView

class WeatherList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherList = intent.extras?.get("list") as ArrayList<WeatherData>
        val cityTo = intent.extras?.getString("cityTo")
        val cityFrom = intent.extras?.getString("cityFrom")
        setContentView(R.layout.weather_list_activity)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = ""
        tvDirection = findViewById(R.id.tvDirection)
        tvDirection.text = "$cityFrom — $cityTo"
        recyclerView = findViewById(R.id.weatherList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(weatherList)


    }
}