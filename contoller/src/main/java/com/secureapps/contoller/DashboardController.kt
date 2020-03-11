package com.secureapps.contoller

import com.secureapps.interactor.DashboardInteractor
import com.secureapps.presenter.DashboardPresenter
import javax.inject.Inject

class DashboardController @Inject constructor(
    private val dashboardPresenter: DashboardPresenter,
    private val dashboardInteractor: DashboardInteractor
) {
}