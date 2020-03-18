package com.secureapps.todo.di

import android.content.Context
import androidx.room.Room
import com.secureapps.gateway.ToDoDAO
import com.secureapps.gatewayimpl.ToDoDatabase
import com.secureapps.todo.ToDoGlobalApp
import com.secureapps.todo.scopes.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ToDoModule {

    @AppScope
    @Provides
    fun getDatabase(context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java, "TODO_Database"
        ).build()
    }

    @Provides
    @AppScope
    fun getContext(globalApp: ToDoGlobalApp):Context{
        return globalApp;
    }

    @Provides
    fun getToDoGateway(database: ToDoDatabase) : ToDoDAO{
        return database.toDoDAO()
    }
}