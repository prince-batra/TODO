package com.secureapps.todo.activityFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.secureapps.contoller.DashboardController
import com.secureapps.todo.R
import com.secureapps.todo.di.DaggerAppComponent
import com.secureapps.todo.viewHolder.DashboardViewHolder
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class Dashboard : DaggerAppCompatActivity() {

    //lateinit var calendarView : CalendarView

    @Inject
    lateinit var dashboardController: DashboardController
    lateinit var viewHolder: DashboardViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        viewHolder = DashboardViewHolder(this)
        viewHolder.bind(dashboardController)
        setContentView(viewHolder.rootView())
        Handler().postDelayed(Runnable { viewHolder.screenCreated() }, 1000);
    }

}
