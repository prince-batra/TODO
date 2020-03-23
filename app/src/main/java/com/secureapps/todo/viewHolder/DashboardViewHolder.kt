package com.secureapps.todo.viewHolder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.jakewharton.rxbinding3.view.clicks
import com.secureapps.contoller.DashboardController
import com.secureapps.todo.R
import com.secureapps.todo.activityFragment.AddToDoDialogFragment
import com.secureapps.todo.adapters.TasksListAdapter
import com.secureapps.todo.databinding.DasboardLayoutBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import java.util.concurrent.TimeUnit


class DashboardViewHolder(private val context: Context) : ViewModel() {

    lateinit var binding: DasboardLayoutBinding
    private var disposables = CompositeDisposable()
    lateinit var selectedDate: Calendar
    lateinit var dashboardController: DashboardController
    lateinit var adapter: TasksListAdapter

    fun bind(controller: DashboardController) {
        dashboardController = controller;
        binding = DasboardLayoutBinding.inflate(LayoutInflater.from(context))
        binding.dashboardViewHolder = this
        binding.dashboardViewData = controller.viewData()
        initValues()
        bindCalender()
        bindRecyclerView()
        bindFloatingButton()
        subscribeForViewChange();
    }

    private fun bindRecyclerView() {
        binding.taskRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TasksListAdapter((context as AppCompatActivity).layoutInflater)
        }
        adapter = binding.taskRecyclerView.adapter as TasksListAdapter
    }

    private fun subscribeForViewChange() {
        dashboardController.viewData().dotsBehaviorSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.calendarView.setEvents(it)
            }.apply { disposables.add(this) }

        dashboardController.viewData().taskListSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter.updateList(it)
            }.apply { disposables.add(this) }

    }

    fun screenCreated() {
        dashboardController.getToDoCount(selectedDate)
        dashboardController.getDots()
        getTaskOfDate()
    }

    fun getTaskOfDate() {
        dashboardController.getSelectedDateTask(selectedDate).apply { disposables.add(this) }
    }

    private fun bindFloatingButton() {
        binding.btnAdd.clicks().throttleFirst(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val appCompatActivity: AppCompatActivity = context as AppCompatActivity
                AddToDoDialogFragment().apply {
                    val bundle = Bundle()
                    bundle.putInt("year", selectedDate.get(Calendar.YEAR))
                    bundle.putInt("month", selectedDate.get(Calendar.MONTH))
                    bundle.putInt("day", selectedDate.get(Calendar.DAY_OF_MONTH))
                    arguments = bundle
                }.show(appCompatActivity.supportFragmentManager, "addToDo")
            }.apply { disposables.add(this) }
    }

    private fun initValues() {
        selectedDate = Calendar.getInstance()
    }

    private fun bindCalender() {
        binding.calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                selectedDate = eventDay.calendar
                dashboardController.getToDoCount(selectedDate)
                getTaskOfDate()
            }
        })

    }

    fun rootView(): View {
        return binding.root
    }


}