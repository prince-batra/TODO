package com.secureapps.gatewayimpl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secureapps.entity.Task
import com.secureapps.gateway.ToDoDAO

@Database(entities = arrayOf(Task::class), version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDAO() :ToDoDAO
}