package com.secureapps.entity

import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object{
        fun getFormattedDate(cal: Calendar, stringFormat: String): String {
            val format = SimpleDateFormat(stringFormat, Locale.US)
            return format.format(cal.time)
        }
    }


}