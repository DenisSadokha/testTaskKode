package com.example.testtask.buyTicket.main.dialogs

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import com.example.testtask.R

class Loading(var c: Activity) : Dialog(c) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
    }
}