package com.secureapps.todo.di

import android.content.Context
import androidx.room.Room
import com.secureapps.gatewayimpl.ToDoDatabase
import com.secureapps.todo.scopes.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ToDoModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java, "TODO_Database"
        ).build()
    }
}