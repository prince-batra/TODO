package com.secureapps.presenter

import androidx.databinding.ObservableField
import javax.inject.Inject

class DashboardViewData @Inject constructor() {
    val message = ObservableField<String>()

}