package com.secureapps.entity

import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object{
        fun getFormattedDate(cal: Calendar, stringFormat: String): String {
            val format = SimpleDateFormat(stringFormat, Locale.US)
            return format.format(cal.time)
        }

        fun getCalendarInstance(date : String) : Calendar{
            val calc:Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US)
            calc.time = sdf.parse(date)!!
            return calc
        }

    }


}