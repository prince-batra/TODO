package com.secureapps.todo.viewHolder

import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.secureapps.contoller.AddToDoController
import com.secureapps.todo.activityFragment.AddToDoDialogFragment
import com.secureapps.todo.databinding.AddTodoLayoutBinding
import java.util.*

class AddToDoViewHolder(val context: Context,
                        val dismissBottomSheet: () -> Unit) : ViewModel() {

    lateinit var binding: AddTodoLayoutBinding
    val date = ObservableField<String>("CurrentDate")
    val title = ObservableField<String>()
    val startTime = ObservableField<String>()
    val endTime = ObservableField<String>()
    val remindMe: ObservableBoolean = ObservableBoolean(true)
    val pickedColor = ObservableField<String>("#6200EE")


    fun rootView(): View {
        return binding.root
    }

    fun bind(addToDoController: AddToDoController) {
        binding = AddTodoLayoutBinding.inflate(LayoutInflater.from(context))
        initValues()
        binding.addToDo = this
    }

    private fun initValues() {
        startTime.set("" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE))
        endTime.set("" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE))
    }


    var startTimeListener: View.OnClickListener = View.OnClickListener {
        TimePickerDialog(
            context,
            { timePicker, selectedHour, selectedMinute ->
                startTime.set("" + selectedHour + ":" + selectedMinute)
            },
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
            Calendar.getInstance().get(Calendar.MINUTE),
            true
        ).apply {
            setTitle("Select Start Time")
            show()
        }
    }

    var endTimeListener: View.OnClickListener = View.OnClickListener {
        TimePickerDialog(
            context,
            { timePicker, selectedHour, selectedMinute ->
                endTime.set("" + selectedHour + ":" + selectedMinute)
            },
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
            Calendar.getInstance().get(Calendar.MINUTE),
            true
        ).apply {
            setTitle("Select End Time")
            show()
        }
    }

    var saveButtonListener: View.OnClickListener = View.OnClickListener {
        dismissBottomSheet()
    }

    var closeListener: View.OnClickListener = View.OnClickListener {
        dismissBottomSheet()
    }

    var pickColorListener: View.OnClickListener = View.OnClickListener {
         MaterialColorPickerDialog
           .Builder(context as AppCompatActivity)
           .setColorShape(ColorShape.SQAURE)    // Default ColorShape.CIRCLE
           .setColorSwatch(ColorSwatch._800)    // Default ColorSwatch._500
           .setDefaultColor(Color.parseColor(pickedColor.get()))    // Pass Default Color
           .setColorListener { color, colorHex ->
               pickedColor.set(colorHex)
           }
           .show()
    }


}