package com.example.testtask.buyTicket.main.findTicket

import android.text.format.Time
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.testtask.buyTicket.data.DataForFrom
import com.example.testtask.buyTicket.data.DataForTo
import com.example.testtask.buyTicket.data.WeatherData
import com.example.testtask.buyTicket.main.dialogs.DatePicker
import com.example.testtask.buyTicket.service.network.receiveWeather.ReceiveWeather

@InjectViewState
class PBuyTicket : MvpPresenter<VBuyTicket>() {
    var dataTo: DataForTo? = null
    var dataFrom: DataForFrom? = null
    private val finalList = ArrayList<WeatherData>()
    private var finalCount = 0
    fun test() {
        viewState.findTickets()
    }

    fun clearWeatherList() {
        finalCount = 0
        finalList.clear()
    }

    fun onClickDateChoose(isTo: Boolean) {
        val today = Time(Time.getCurrentTimezone())
        today.setToNow()
        today.monthDay
        val choose = DatePicker(
            today.monthDay,
            today.month,
            today.year,
            isTo
        )
        viewState.openDatePicker(choose)
    }

    fun addCity(city: String, where: Int) {
        when (where) {
            CITY_TO -> {
                dataTo = DataForTo(city)
            }
            CITY_FROM -> {
                dataFrom = DataForFrom(city)
            }
        }

    }

    fun reverseCity(from: String, to: String) {
        val newFrom = to
        val newTo = from
        dataTo = DataForTo(newTo)
        dataFrom = DataForFrom(newFrom)
        viewState.reverseCity(from = newFrom, to = newTo)

    }

    fun findTickets() {
        val receiveWeather = ReceiveWeather(dataFrom!!.city, this)
        receiveWeather.makeRequest()

        val receiveWeatherTo = ReceiveWeather(dataTo!!.city, this)
        receiveWeatherTo.makeRequest()

    }

    fun receiveSuccess(list: ArrayList<WeatherData>) {
        println(finalCount)
        finalCount += 1
        finalList.addAll(list)
        if (finalCount == 2) {
            viewState.goToAll(finalList, cityFrom = dataFrom!!.city, cityTo = dataTo!!.city)
        }
    }


    fun checkValidPassenger(f: String, s: String, t: String, isKid: Boolean): Boolean {
        val sum = f.toInt() + s.toInt() + t.toInt()
        if (sum >= 9) {
            viewState.invalidPassenger("Пассажиров должно быть не более 9 человек")
            return false
        }
        if (isKid) {
            if (t.toInt() >= f.toInt()) {
                viewState.invalidPassenger("Младенцев не должно быть больше, чем взрослых")
                return false
            }
        }
        return true
    }


}