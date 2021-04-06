package com.example.testtask.buyTicket.main.findTicket

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.testtask.buyTicket.data.WeatherData
import com.example.testtask.buyTicket.main.dialogs.DatePicker

@StateStrategyType(OneExecutionStateStrategy::class)
interface VBuyTicket : MvpView {
    fun findTickets()
    fun invalidPassenger(message: String)
    fun openDatePicker(choose: DatePicker)
    fun reverseCity(from: String, to:String)
    fun goToAll(list: ArrayList<WeatherData>, cityFrom: String, cityTo: String)
//    fun incKid(now: Int)
//    fun incBaby(now: Int)
//    fun decMen(now: Int)
//    fun decKid(now: Int)
//    fun decBaby(now: Int)
//    fun reverseWay()


}