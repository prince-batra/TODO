package com.secureapps.presenter

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DashboardPresenter @Inject constructor(val viewData: DashboardViewData) {


    fun subscribeToDoCount(observable: Flowable<Int>): Disposable {
        return observable.observeOn(AndroidSchedulers.mainThread()).subscribe { value ->
            viewData.message.set("You have " + value + " tasks for the day.")
        }
    }

}