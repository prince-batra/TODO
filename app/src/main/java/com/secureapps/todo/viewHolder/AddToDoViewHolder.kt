package com.secureapps.todo.viewHolder

import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.jakewharton.rxbinding3.widget.textChanges
import com.secureapps.contoller.AddToDoController
import com.secureapps.entity.Task
import com.secureapps.todo.databinding.AddTodoLayoutBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class AddToDoViewHolder(
    val context: Context,
    val calendar: Calendar,
    val dismissBottomSheet: () -> Unit
) : ViewModel() {

    lateinit var binding: AddTodoLayoutBinding
    lateinit var controller: AddToDoController
    private var disposables = CompositeDisposable()

    fun rootView(): View {
        return binding.root
    }

    override fun onCleared() {
        disposables.dispose()
        disposables = CompositeDisposable()
        super.onCleared()
    }

    fun bind(addToDoController: AddToDoController) {
        binding = AddTodoLayoutBinding.inflate(LayoutInflater.from(context))
        controller = addToDoController
        binding.viewHolder = this
        binding.apply {
            addToDo = controller.viewData().apply { setDefaultState() }
        }

        bindCurrentDate()
        bindTextWatcher()
        bindRemindMe()
        bindTimers()
        bindColorPicker()
        bindSaveButton()
        bindCloseButton()
    }

    private fun bindCurrentDate() {
       controller.bindCurrentDate(calendar)
    }

    private fun bindRemindMe() {
        binding.checkboxRemindMe.checkedChanges().observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                controller.viewData().remindMe.set(it)
            }.apply { disposables.add(this) }
    }

    private fun bindTextWatcher() {
        controller.userTitle(binding.edtTitle.textChanges().debounce(
            1,
            TimeUnit.SECONDS
        ).observeOn(AndroidSchedulers.mainThread())
            .map { t: CharSequence -> t.toString() }).apply { this }
    }

    private fun bindCloseButton() {
        binding.imgClose.clicks().throttleFirst(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                dismissBottomSheet()
            }.apply { disposables.add(this) }


    }

    private fun bindSaveButton() {
        controller.saveButtonClicked(
            binding.btnSave.clicks().throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()), calendar
        ).apply { disposables.add(this) }
    }


    private fun bindColorPicker() {
        controller.colorPicker(
            binding.colorSelectionLayout.clicks().throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()), context
        ).apply { disposables.add(this) }
    }

    private fun bindTimers() {
        controller.startTimerClicked(
            Observable.merge(binding.txtStartTime.clicks(), binding.startTime.clicks())
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()), context
        ).apply { disposables.add(this) }

        controller.endTimerClicked(
            Observable.merge(binding.txtEndTime.clicks(), binding.endTime.clicks())
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()), context
        ).apply { disposables.add(this) }
    }


}