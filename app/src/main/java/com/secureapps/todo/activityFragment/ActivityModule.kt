package com.secureapps.todo.activityFragment

import android.content.Context
import com.secureapps.todo.ToDoGlobalApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule() {

    @ContributesAndroidInjector
    abstract fun dashboardActivity(): Dashboard

    @ContributesAndroidInjector
    abstract fun addToDoFragment(): AddToDoDialogFragment

}