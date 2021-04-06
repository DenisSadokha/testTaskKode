package com.example.testtask.buyTicket.main.listOfWeather.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.buyTicket.data.WeatherData

class Adapter(private val content: ArrayList<WeatherData>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMinTemp: TextView? = null
        var tvMaxTemp: TextView? = null
        var tvMainInfo: TextView? = null
        var tvFeelsLike: TextView? = null
        var tvWeather: TextView? = null
        var imageStatusWeather: ImageView? = null

        init {
            tvFeelsLike = itemView.findViewById(R.id.tvFeelsLike)
            tvMinTemp = itemView.findViewById(R.id.tvMinTemp)
            tvMainInfo = itemView.findViewById(R.id.tvMainInfo)
            tvMaxTemp = itemView.findViewById(R.id.tvMaxTemp)
            tvWeather = itemView.findViewById(R.id.tvWeather)
            imageStatusWeather = itemView.findViewById(R.id.imageStatusWeather)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_list_card, parent, false)
        return ViewHolder(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherAll = content.get(position)
        holder.tvMaxTemp?.text = "${weatherAll.maxTemp} °F"
        holder.tvMinTemp?.text = "${weatherAll.minTemp} °F"
        holder.tvMainInfo?.text = "${weatherAll.temp} °F in ${weatherAll.city}"
        holder.tvFeelsLike?.text = "Feels like ${weatherAll.feelsLike} °F"
        holder.tvWeather?.text = weatherAll.weather.replace("\"", "")
        when(weatherAll.weather.replace("\"", "")){
            "Rain" -> holder.imageStatusWeather?.setImageResource(R.drawable.ic_rain)
            "Clear" -> holder.imageStatusWeather?.setImageResource(R.drawable.ic_sunny)
            "Snow" -> holder.imageStatusWeather?.setImageResource(R.drawable.ic_snow)
            "Clouds" -> holder.imageStatusWeather?.setImageResource(R.drawable.ic_cloudy)
            else -> holder.imageStatusWeather?.setImageResource(R.drawable.kid_active)
        }

    }

    override fun getItemCount() = content.size

}