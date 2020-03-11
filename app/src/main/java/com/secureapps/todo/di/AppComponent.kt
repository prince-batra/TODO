package com.secureapps.todo.di

import com.secureapps.todo.ToDoGlobalApp
import com.secureapps.todo.activityFragment.ActivityModule
import com.secureapps.todo.scopes.AppScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class, ToDoModule::class, ActivityModule::class]
)
interface AppComponent : AndroidInjector<ToDoGlobalApp> {


    @Component.Factory
    interface Factory : AndroidInjector.Factory<ToDoGlobalApp> {

    }
}