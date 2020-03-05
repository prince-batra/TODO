package com.secureapps.todo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){

    lateinit var calendarView : CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendarView = findViewById(R.id.calendarView)
        val calc = Calendar.getInstance()
        calc.set(2020,2,6,18,0,0)

        val list = ArrayList<EventDay>()
        val eventDay = EventDay(calc,R.drawable.red_circle)
        list.add(eventDay)
        calendarView.setEvents(list)
    }
}
