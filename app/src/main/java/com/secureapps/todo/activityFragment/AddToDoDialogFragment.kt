package com.secureapps.todo.activityFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.secureapps.contoller.AddToDoController
import com.secureapps.todo.viewHolder.AddToDoViewHolder
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import java.util.*
import javax.inject.Inject

class AddToDoDialogFragment : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var addToDoController: AddToDoController

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any?>? {
        return androidInjector
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        AndroidSupportInjection.inject(this)
        return initView()
    }

    private fun getSelectedDate(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, arguments!!.getInt("year"))
        calendar.set(Calendar.MONTH, arguments!!.getInt("month"))
        calendar.set(Calendar.DAY_OF_MONTH, arguments!!.getInt("day"))
        return calendar
    }

    private fun initView(): View {
        return AddToDoViewHolder(activity as AppCompatActivity,
            getSelectedDate()
            , {
                dismiss()
            }).apply { bind(addToDoController) }.rootView()
    }

}