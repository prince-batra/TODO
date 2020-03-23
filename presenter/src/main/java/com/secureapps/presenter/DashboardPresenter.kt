package com.secureapps.presenter

import com.applandeo.materialcalendarview.EventDay
import com.secureapps.entity.Task
import com.secureapps.entity.Util
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DashboardPresenter @Inject constructor(val viewData: DashboardViewData) {


    fun subscribeToDoCount(observable: Flowable<Int>): Disposable {
        return observable.observeOn(AndroidSchedulers.mainThread()).subscribe { value ->
            viewData.message.set("You have " + value + " tasks for the day.")
        }
    }


    fun subscribeForDots(observable: Flowable<List<Task>>): Disposable {
        return observable.flatMapSingle { task ->
            Observable.fromIterable(task)
                .map { singleTask ->
                    EventDay(
                        Util.getCalendarInstance(singleTask.startTime),
                        R.drawable.red_circle
                    )
                }
                .toList()
        }.subscribe { transformedList ->
            viewData.dotsBehaviorSubject.onNext(transformedList)
        }
    }

    fun subscribeForTasksList(observable: Flowable<List<SingleTaskViewData>>) : Disposable{
        return observable.observeOn(AndroidSchedulers.mainThread()).subscribe {
            viewData.taskListSubject.onNext(it)
        }
    }

}