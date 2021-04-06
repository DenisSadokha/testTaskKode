package com.example.testtask.buyTicket.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

fun Context.toast(message: String, dur: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, dur).show()
}
