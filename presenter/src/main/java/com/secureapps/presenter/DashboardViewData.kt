package com.secureapps.presenter

import androidx.databinding.ObservableField
import com.applandeo.materialcalendarview.EventDay
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class DashboardViewData @Inject constructor() {

    val dotsBehaviorSubject : BehaviorSubject<List<EventDay>> = BehaviorSubject.create()
    val message = ObservableField<String>()
    val taskListSubject : BehaviorSubject<List<SingleTaskViewData>> = BehaviorSubject.create()

}