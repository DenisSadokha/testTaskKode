package com.example.testtask.buyTicket.main.findTicket

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.testtask.R
import com.example.testtask.buyTicket.data.WeatherData
import com.example.testtask.buyTicket.main.dialogs.DatePicker
import com.example.testtask.buyTicket.main.dialogs.Loading
import com.example.testtask.buyTicket.main.findTicket.chooseCity.ChooseCity
import com.example.testtask.buyTicket.main.listOfWeather.WeatherList
import com.example.testtask.buyTicket.utils.toast
import java.io.Serializable
import java.text.DateFormat
import java.util.*

const val CITY_TO = 1
const val CITY_FROM = 2

class BuyActivity : MvpAppCompatActivity(), VBuyTicket, View.OnClickListener,
    DatePicker.DateChoose {
    lateinit var toolbar: Toolbar
    lateinit var btnAddFrom: Button
    lateinit var btnIncMen: ImageButton
    lateinit var btnIncKid: ImageButton
    lateinit var btnIncBaby: ImageButton
    lateinit var btnDecMen: ImageButton
    lateinit var btnDecKid: ImageButton
    lateinit var btnDecBaby: ImageButton
    lateinit var btnRemoveDateFrom: ImageButton
    lateinit var tvCountMen: TextView
    lateinit var tvMen: TextView
    lateinit var iMen: ImageView
    lateinit var tvCountBaby: TextView
    lateinit var tvBaby: TextView
    lateinit var iBaby: ImageView
    lateinit var tvCountKid: TextView
    lateinit var tvKid: TextView
    lateinit var iKid: ImageView
    lateinit var btnChooseDateTo: Button
    lateinit var tvDateTo: TextView
    lateinit var btnChooseDateFrom: Button
    lateinit var tvDateFrom: TextView
    lateinit var viewGroupAddFrom: ViewGroup
    lateinit var viewGroupFrom: ViewGroup
    lateinit var btnFindTickets: Button
    lateinit var btnTo: Button
    lateinit var btnFrom: Button
    lateinit var tvCityFrom: TextView
    lateinit var tvCityTo: TextView
    lateinit var iBtnReverse: ImageButton
    var isOnlyTo = true
    lateinit var load: Loading

    @InjectPresenter
    lateinit var pBuyTicket: PBuyTicket
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null) return
        when (requestCode) {
            CITY_TO -> {
                tvCityTo.text = data.getStringExtra("city").toString()
                pBuyTicket.addCity(data.getStringExtra("city").toString(), CITY_TO)

            }
            CITY_FROM -> {
                tvCityFrom.text = data.getStringExtra("city").toString()
                pBuyTicket.addCity(data.getStringExtra("city").toString(), CITY_FROM)

            }

        }

    }

    override fun onStop() {
        super.onStop()
        pBuyTicket.clearWeatherList()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        btnAddFrom = findViewById(R.id.btnAddFrom)
        btnIncMen = findViewById(R.id.iBtnIncMen)
        btnIncKid = findViewById(R.id.iBtnIncKid)
        btnIncBaby = findViewById(R.id.iBtnIncBaby)
        btnDecMen = findViewById(R.id.iBtnDecMen)
        btnDecBaby = findViewById(R.id.iBtnDecBaby)
        btnDecKid = findViewById(R.id.iBtnDecKid)
        tvCountMen = findViewById(R.id.tvCountMen)
        tvMen = findViewById(R.id.nothingVz)
        iMen = findViewById(R.id.nothingMen)
        tvCountBaby = findViewById(R.id.tvCountBaby)
        tvBaby = findViewById(R.id.nothingVoz)
        iBaby = findViewById(R.id.nothingImgBaby)
        tvCountKid = findViewById(R.id.tvCountKid)
        tvKid = findViewById(R.id.nothingDo2)
        iKid = findViewById(R.id.nothingKid)
        btnChooseDateTo = findViewById(R.id.btnChooseDateTo)
        tvDateTo = findViewById(R.id.tvDateTo)
        viewGroupAddFrom = findViewById(R.id.viewGroupAddFrom)
        viewGroupFrom = findViewById(R.id.viewGroupFrom)
        btnRemoveDateFrom = findViewById(R.id.iBtnRemoveDateFrom)
        btnChooseDateFrom = findViewById(R.id.btnChooseDateFrom)
        tvDateFrom = findViewById(R.id.tvDateFrom)
        btnFindTickets = findViewById(R.id.btnFindTickets)
        btnTo = findViewById(R.id.btnTo)
        btnFrom = findViewById(R.id.btnFrom)
        tvCityFrom = findViewById(R.id.tvCityFrom)
        tvCityTo = findViewById(R.id.tvCityTo)
        iBtnReverse = findViewById(R.id.iBtnReverse)
        initCity()

        btnTo.setOnClickListener(this)
        iBtnReverse.setOnClickListener(this)
        btnFrom.setOnClickListener(this)
        btnFindTickets.setOnClickListener(this)
        btnChooseDateFrom.setOnClickListener(this)
        btnRemoveDateFrom.setOnClickListener(this)
        btnAddFrom.setOnClickListener(this)
        btnIncMen.setOnClickListener(this)
        btnDecMen.setOnClickListener(this)
        btnIncBaby.setOnClickListener(this)
        btnDecBaby.setOnClickListener(this)
        btnIncKid.setOnClickListener(this)
        btnDecKid.setOnClickListener(this)
        btnChooseDateTo.setOnClickListener(this)
        btnDecBaby.isEnabled = false
        btnDecMen.isEnabled = false
        btnDecKid.isEnabled = false

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.buy_menu, menu)
        return true
    }

    override fun findTickets() {
        Toast.makeText(this, "test", Toast.LENGTH_LONG).show()
    }

    override fun invalidPassenger(message: String) {
        toast(message)
    }

    override fun openDatePicker(choose: DatePicker) {
        choose.show(supportFragmentManager, "pick")
    }

    override fun reverseCity(from: String, to: String) {
        tvCityFrom.text = from
        tvCityTo.text = to
    }

    override fun goToAll(list: ArrayList<WeatherData>, cityFrom: String, cityTo: String) {
        println(list)
        load.dismiss()
        val intent = Intent(this, WeatherList::class.java)
        intent.putExtra("cityFrom", cityFrom)
        intent.putExtra("cityTo", cityTo)
        intent.putExtra("list", list as Serializable)
        startActivity(intent)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.iBtnIncMen -> {
                if (pBuyTicket.checkValidPassenger(
                        tvCountMen.text.toString(),
                        tvCountBaby.text.toString(),
                        tvCountKid.text.toString(),
                        false
                    )
                ) {
                    enableColumn(btnDecMen, tvCountMen, tvMen, iMen, 228)
                    tvMen.text = "Взрослых"
                    tvCountMen.text = tvCountMen.text.toString().toInt().inc().toString()
                }
            }
            R.id.iBtnDecMen -> {
                tvCountMen.text = tvCountMen.text.toString().toInt().dec().toString()
                if (tvCountMen.text.toString().toInt() == 1) {
                    btnDecMen.isEnabled = false
                    btnDecMen.setImageDrawable(getDrawable(R.drawable.remove_unable))
                    tvMen.text = "Взрослый"
                }
            }
            R.id.iBtnIncBaby -> {
                if (pBuyTicket.checkValidPassenger(
                        tvCountMen.text.toString(),
                        tvCountBaby.text.toString(),
                        tvCountKid.text.toString(),
                        false
                    )
                ) {
                    tvCountBaby.text = tvCountBaby.text.toString().toInt().inc().toString()
                    enableColumn(btnDecBaby, tvBaby, tvCountBaby, iBaby, 1)
                }
            }
            R.id.iBtnDecBaby -> {
                tvCountBaby.text = tvCountBaby.text.toString().toInt().dec().toString()
                if (tvCountBaby.text.toString().toInt() == 0) {
                    unableColumn(btnDecBaby, tvCountBaby, tvBaby, iBaby, 1)
                }
            }
            R.id.iBtnIncKid -> {
                if (pBuyTicket.checkValidPassenger(
                        tvCountMen.text.toString(),
                        tvCountBaby.text.toString(),
                        tvCountKid.text.toString(),
                        true
                    )
                ) {
                    tvCountKid.text = tvCountKid.text.toString().toInt().inc().toString()
                    enableColumn(btnDecKid, tvKid, tvCountKid, iKid, 0)
                }
            }
            R.id.iBtnDecKid -> {
                tvCountKid.text = tvCountKid.text.toString().toInt().dec().toString()
                if (tvCountKid.text.toString().toInt() == 0) {
                    unableColumn(btnDecKid, tvKid, tvCountKid, iKid, 0)
                }
            }
            R.id.btnChooseDateTo -> {
                pBuyTicket.onClickDateChoose(true)
            }
            R.id.btnAddFrom -> {
                pBuyTicket.onClickDateChoose(false)
            }
            R.id.iBtnRemoveDateFrom -> {
                viewGroupFrom.visibility = View.GONE
                viewGroupAddFrom.visibility = View.VISIBLE
                isOnlyTo = true
            }
            R.id.btnFindTickets -> {
                pBuyTicket.findTickets()
                startLoad()
            }
            R.id.btnFrom -> {
                val intent = Intent(this, ChooseCity::class.java)
                startActivityForResult(intent, CITY_FROM)

            }
            R.id.btnTo -> {
                val intent = Intent(this, ChooseCity::class.java)
                startActivityForResult(intent, CITY_TO)
            }
            R.id.iBtnReverse -> {
                pBuyTicket.reverseCity(tvCityFrom.text.toString(), tvCityTo.text.toString())

            }

        }
    }
    private fun startLoad(){
         load = Loading(this)
        load.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        load.setCancelable(false)
        load.show()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun enableColumn(
        btn: ImageButton,
        text: TextView,
        text2: TextView,
        image: ImageView,
        who: Int
    ) {
        btn.isEnabled = true
        btn.setImageDrawable(getDrawable(R.drawable.enable_remove))
        text.setTextColor(Color.parseColor("#000000"))
        text2.setTextColor(Color.parseColor("#000000"))
        when (who) {
            0 -> image.setImageDrawable(getDrawable(R.drawable.baby_active))
            1 -> image.setImageDrawable(getDrawable(R.drawable.kid_active))
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun unableColumn(
        btn: ImageButton,
        text: TextView,
        text2: TextView,
        image: ImageView,
        who: Int
    ) {
        btn.isEnabled = false
        btn.setImageDrawable(getDrawable(R.drawable.remove_unable))
        text.setTextColor(Color.parseColor("#909191"))
        text2.setTextColor(Color.parseColor("#909191"))
        when (who) {
            0 -> image.setImageDrawable(getDrawable(R.drawable.ic_icn_booking_passenger_baby))
            1 -> image.setImageDrawable(getDrawable(R.drawable.ic_icn_booking_passenger_kid))
        }
    }

    override fun chooseDateTo(day: Int, month: Int, year: Int) {
        val dateCalendar: Calendar = Calendar.getInstance()
        dateCalendar.set(Calendar.DAY_OF_MONTH, day)
        dateCalendar.set(Calendar.YEAR, year)
        dateCalendar.set(Calendar.MONTH, month)
        println()
        tvDateTo.text = DateFormat.getDateInstance().format(dateCalendar.time)


    }

    override fun chooseDateFrom(day: Int, month: Int, year: Int) {
        val dateCalendar: Calendar = Calendar.getInstance()
        dateCalendar.set(Calendar.DAY_OF_MONTH, day)
        dateCalendar.set(Calendar.YEAR, year)
        dateCalendar.set(Calendar.MONTH, month)
        tvDateFrom.text = DateFormat.getDateInstance().format(dateCalendar.time)
        viewGroupAddFrom.visibility = View.GONE
        viewGroupFrom.visibility = View.VISIBLE
        isOnlyTo = false

    }
    fun initCity(){
        pBuyTicket.addCity("Kemerovo", CITY_FROM)
        pBuyTicket.addCity("Moscow", CITY_TO)
        tvCityTo.text = "Moscow"
        tvCityFrom.text = "Kemerovo"
    }
}