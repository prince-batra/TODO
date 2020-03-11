package com.secureapps.todo.activityFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule  {

    @ContributesAndroidInjector
    abstract fun dashboardActivity() : Dashboard

    @ContributesAndroidInjector
    abstract fun addToDoFragment() : AddToDoDialogFragment

}