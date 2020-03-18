package com.secureapps.presenter

import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.util.AndroidException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.secureapps.entity.TODO_RESULT
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class AddToDoPresenter @Inject constructor(val viewData: AddToDoViewData) {

    fun showLoading() {
        viewData.showLoading.set(true)
    }

    fun subscribeSaveToDo(observable: Observable<Long>): Disposable {
        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: Long ->
                if (result != 0L) {
                    viewData.toastBehaviour.onNext("Successfully Created");
                    viewData.bottomSheetClosedSubject.onNext(true);
                }
                else
                    viewData.toastBehaviour.onNext("Failed to Create ToDo");

                hideLoading()
            }
    }

    fun subscribeTitleChange(observable: Observable<String>): Disposable {
        return observable.subscribe {
            viewData.title.set(it)
        }
    }

    fun subscribeStartTimerClick(observable: Observable<Unit>, context: Context): Disposable {
        return observable.subscribe {
            TimePickerDialog(
                context,
                { timePicker, selectedHour, selectedMinute ->
                    viewData
                        .startTime.set("" + selectedHour + ":" + selectedMinute)
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true
            ).apply {
                setTitle("Select Start Time")
                show()
            }
        }
    }

    fun subscribeEndTimerClick(observable: Observable<Unit>, context: Context): Disposable {
        return observable.subscribe {
            TimePickerDialog(
                context,
                { timePicker, selectedHour, selectedMinute ->
                    viewData
                        .endTime.set("" + selectedHour + ":" + selectedMinute)
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true
            ).apply {
                setTitle("Select Start Time")
                show()
            }
        }
    }

    fun subscribeColorPicker(observable: Observable<Unit>, context: Context): Disposable {
        return observable.subscribe {
            MaterialColorPickerDialog
                .Builder(context as AppCompatActivity)
                .setColorShape(ColorShape.SQAURE)    // Default ColorShape.CIRCLE
                .setColorSwatch(ColorSwatch._800)    // Default ColorSwatch._500
                .setDefaultColor(Color.parseColor(viewData.pickedColor.get()))    // Pass Default Color
                .setColorListener { color, colorHex ->
                    viewData.pickedColor.set(colorHex)
                }
                .show()
        }


    }

    fun hideLoading() {
        viewData.showLoading.set(false)
    }

    fun showValidationError(){
        viewData.error.set("Enter Title")
    }

    fun showCurrentDate(string: String){
        viewData.date.set(string)
    }
}