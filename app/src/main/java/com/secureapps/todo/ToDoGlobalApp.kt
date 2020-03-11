package com.secureapps.todo

import com.secureapps.todo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ToDoGlobalApp : DaggerApplication() {

    lateinit var injector: AndroidInjector<ToDoGlobalApp>

    override fun applicationInjector(): AndroidInjector<ToDoGlobalApp> {
        return injector
    }

    override fun onCreate() {
        this.injector = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }

}