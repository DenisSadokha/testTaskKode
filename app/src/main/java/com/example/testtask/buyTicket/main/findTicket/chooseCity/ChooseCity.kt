package com.example.testtask.buyTicket.main.findTicket.chooseCity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.testtask.R
import com.example.testtask.buyTicket.data.DataForList

class ChooseCity : AppCompatActivity() {
    lateinit var  list: ListView
    lateinit var btn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_city_activity)
        list = findViewById(R.id.list)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        val arrayCity = ArrayList<String>()
        arrayCity.add("Kemerovo")
        arrayCity.add("Moscow")
        arrayCity.add("Kaliningrad")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayCity)
        list.adapter = adapter
        list.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val data = Intent()
            data.putExtra("city",list.getItemAtPosition(i).toString())
            setResult(RESULT_OK,data)
            finish()
        }


    }
}
