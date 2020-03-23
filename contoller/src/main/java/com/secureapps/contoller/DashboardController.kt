package com.secureapps.contoller

import com.secureapps.entity.Util
import com.secureapps.interactor.DashboardInteractor
import com.secureapps.presenter.DashboardPresenter
import com.secureapps.presenter.DashboardViewData
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

    fun getToDoCount(calendar: Calendar) : Disposable{
        val startTime = Util.getFormattedDate(calendar,"dd MMM yyyy") + " 00:00";
        val endTime = Util.getFormattedDate(calendar,"dd MMM yyyy") + " 23:59";
        return dashboardPresenter.subscribeToDoCount(dashboardInteractor.getTaskCount(startTime,endTime))
    }

    fun getDots() : Disposable {
        return dashboardPresenter.subscribeForDots(dashboardInteractor.getAllEventList());
    }

}