package com.secureapps.contoller

import com.secureapps.entity.Task
import com.secureapps.entity.Util
import com.secureapps.interactor.DashboardInteractor
import com.secureapps.presenter.DashboardPresenter
import com.secureapps.presenter.DashboardViewData
import com.secureapps.presenter.SingleTaskViewData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class DashboardController @Inject constructor(
    private val dashboardPresenter: DashboardPresenter,
    private val dashboardInteractor: DashboardInteractor
) {

    fun viewData(): DashboardViewData {
        return dashboardPresenter.viewData
    }

    fun getToDoCount(calendar: Calendar): Disposable {
        val startTime = Util.getFormattedDate(calendar, "dd MMM yyyy") + " 00:00"
        val endTime = Util.getFormattedDate(calendar, "dd MMM yyyy") + " 23:59"
        return dashboardPresenter.subscribeToDoCount(
            dashboardInteractor.getTaskCount(
                startTime,
                endTime
            )
        )
    }

    fun parseTimeOnly(start: String): String {
        val datehour:String = start.split(":")[0]
        val min:String = start.split(":")[1]
        val hour = datehour.substring(datehour.length - 2,datehour.length)
        return hour + ":" + min
    }

    fun getSelectedDateTask(calendar: Calendar): Disposable {
        val startTime = Util.getFormattedDate(calendar, "dd MMM yyyy") + " 00:00"
        val endTime = Util.getFormattedDate(calendar, "dd MMM yyyy") + " 23:59"
        val tasks: Flowable<List<Task>> = dashboardInteractor.getEventList(startTime, endTime)

        return dashboardPresenter.subscribeForTasksList(tasks.flatMapSingle { task ->
            Observable.fromIterable(task)
                .map { singleTask ->
                    SingleTaskViewData(
                        singleTask.title,
                        parseTimeOnly(singleTask.startTime),
                        parseTimeOnly(singleTask.endTime),
                        if (singleTask.remindMe == 1) "Remind Me" else "",
                        singleTask.color,
                        singleTask.lightColor,
                        singleTask.remindMe
                    )
                }
                .toList()
        })
    }

    fun getDots(): Disposable {
        return dashboardPresenter.subscribeForDots(dashboardInteractor.getAllEventList());
    }

}