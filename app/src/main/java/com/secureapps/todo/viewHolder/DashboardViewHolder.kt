package com.secureapps.todo.viewHolder

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.github.dhaval2404.colorpicker.MaterialColorPickerBottomSheet
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.jakewharton.rxbinding3.view.clicks
import com.secureapps.contoller.DashboardController
import com.secureapps.todo.R
import com.secureapps.todo.activityFragment.AddToDoDialogFragment
import com.secureapps.todo.databinding.DasboardLayoutBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class DashboardViewHolder(private val context: Context) : ViewModel() {

    lateinit var binding: DasboardLayoutBinding
    private var disposables = CompositeDisposable()
    val message = ObservableField<String>()

    var addToDoListener: View.OnClickListener = View.OnClickListener {
        val appCompatActivity: AppCompatActivity = context as AppCompatActivity
        AddToDoDialogFragment().show(appCompatActivity.supportFragmentManager, "addToDo")
    }

    fun bind(dashboardController: DashboardController) {
        binding = DasboardLayoutBinding.inflate(LayoutInflater.from(context))
        binding.dashboardViewHolder = this
    }

    fun rootView(): View {
        return binding.root
    }


}