package com.secureapps.todo.viewHolder

import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.jakewharton.rxbinding3.view.clicks
import com.secureapps.contoller.AddToDoController
import com.secureapps.todo.databinding.AddTodoLayoutBinding
import java.util.*

class AddToDoViewHolder(
    val context: Context,
    val dismissBottomSheet: () -> Unit
) : ViewModel() {

    lateinit var binding: AddTodoLayoutBinding
    lateinit var controller: AddToDoController


    fun rootView(): View {
        return binding.root
    }

    fun bind(addToDoController: AddToDoController) {
        binding = AddTodoLayoutBinding.inflate(LayoutInflater.from(context))
        controller = addToDoController
        binding.viewHolder = this
        binding.apply {
            addToDo = controller.viewData().apply { setDefaultState() }
        }
    }


    var startTimeListener: View.OnClickListener = View.OnClickListener {
        TimePickerDialog(
            context,
            { timePicker, selectedHour, selectedMinute ->
                controller.viewData().startTime.set("" + selectedHour + ":" + selectedMinute)
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
                controller.viewData().endTime.set("" + selectedHour + ":" + selectedMinute)
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
            .setDefaultColor(Color.parseColor(controller.viewData().pickedColor.get()))    // Pass Default Color
            .setColorListener { color, colorHex ->
                controller.viewData().pickedColor.set(colorHex)
            }
            .show()
    }


}