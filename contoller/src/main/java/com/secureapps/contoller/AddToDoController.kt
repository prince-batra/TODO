package com.secureapps.contoller

import android.content.Context
import android.text.TextUtils
import com.secureapps.entity.Task
import com.secureapps.interactor.AddToDoInteractor
import com.secureapps.presenter.AddToDoPresenter
import com.secureapps.presenter.AddToDoViewData
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddToDoController @Inject constructor(
    private val addToDoInteractor: AddToDoInteractor,
    private val addToDoPresenter: AddToDoPresenter
) {

    fun viewData(): AddToDoViewData {
        return addToDoPresenter.viewData
    }

    private fun getTask(calendar: Calendar): Task {
        val startTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()
        startTime.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        startTime.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        startTime.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        startTime.set(Calendar.HOUR_OF_DAY, 0)
        startTime.set(Calendar.MINUTE, 0)

        endTime.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        endTime.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        endTime.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        endTime.set(Calendar.HOUR_OF_DAY, 0)
        endTime.set(Calendar.MINUTE, 0)

        startTime.add(
            Calendar.HOUR_OF_DAY,
            viewData().startTime.get()!!.split(":")[0].toInt()
        )
        startTime.add(
            Calendar.MINUTE,
            viewData().startTime.get()!!.split(":")[1].toInt()
        )

        endTime.add(
            Calendar.HOUR_OF_DAY,
            viewData().endTime.get()!!.split(":")[0].toInt()
        )
        endTime.add(Calendar.MINUTE, viewData().endTime.get()!!.split(":")[1].toInt())

        val task = Task(
            title = viewData().title.get()!!,
            color = viewData().pickedColor.get()!!,
            completed = 0,
            remindMe = if (viewData().remindMe.get()) 1 else 0,
            startTime = getFormattedDate(startTime),
            endTime = getFormattedDate(endTime),
            id = 0
        )
        return task
    }

    private fun getFormattedDate(cal: Calendar): String {
        val format = SimpleDateFormat("dd MMM YYYY HH:mm", Locale.US)
        return format.format(cal.time)
    }

    fun saveToDo(cal: Calendar): Disposable {
        val task = getTask(cal)
        addToDoPresenter.showLoading()
        return addToDoPresenter.subscribeSaveToDo(addToDoInteractor.saveToDoInDatabase(task))
    }

    fun userTitle(map: Observable<String>): Disposable {
        return addToDoPresenter.subscribeTitleChange(map)
    }

    fun startTimerClicked(observable: Observable<Unit>, context: Context): Disposable {
        return addToDoPresenter.subscribeStartTimerClick(observable, context)
    }

    fun endTimerClicked(observable: Observable<Unit>, context: Context): Disposable {
        return addToDoPresenter.subscribeEndTimerClick(observable, context)
    }

    fun colorPicker(observable: Observable<Unit>, context: Context): Disposable {
        return addToDoPresenter.subscribeColorPicker(observable, context)
    }

    fun saveButtonClicked(observable: Observable<Unit>, cal: Calendar) : Disposable{
        return observable.subscribe {
            if (validate())
                saveToDo(cal)
        }
    }

    private fun validate(): Boolean {
        if (TextUtils.isEmpty(viewData().title.get())) {
            addToDoPresenter.showValidationError()
            return false;
        }
        return true
    }

}