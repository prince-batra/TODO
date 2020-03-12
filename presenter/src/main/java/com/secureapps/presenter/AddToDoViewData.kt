package com.secureapps.presenter

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import java.util.*
import javax.inject.Inject

class AddToDoViewData @Inject constructor() {

    val date = ObservableField<String>()
    val title = ObservableField<String>()
    val error = ObservableField<String>()
    val startTime = ObservableField<String>()
    val endTime = ObservableField<String>()
    val remindMe: ObservableBoolean = ObservableBoolean()
    val pickedColor = ObservableField<String>()
    val showLoading : ObservableBoolean = ObservableBoolean()

    fun setDefaultState() {
        date.set("CurrentDate")
        pickedColor.set("#6200EE")
        remindMe.set(true)
        showLoading.set(false)
        error.set(null)
        startTime.set(
            "" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(
                Calendar.MINUTE
            )
        )
        endTime.set(
            "" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(
                Calendar.MINUTE
            )
        )
    }


}