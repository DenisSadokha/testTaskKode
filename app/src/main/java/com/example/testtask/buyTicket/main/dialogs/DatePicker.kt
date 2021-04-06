package com.example.testtask.buyTicket.main.dialogs

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePicker(var day: Int, var month: Int, var year: Int,var isTo: Boolean) : DialogFragment(), OnDateSetListener  {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        return DatePickerDialog(
            activity!!,
            this,
            year,
            month,
            day
        )
    }
    interface DateChoose {
        fun chooseDateTo(day: Int, month: Int, year: Int)
        fun chooseDateFrom(day: Int, month: Int, year: Int)

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val dateChoose = activity as DateChoose?
        if(isTo){
            dateChoose?.chooseDateTo(p3,p2,p1)
        } else {
            dateChoose?.chooseDateFrom(p3,p2,p1)
        }


    }
}