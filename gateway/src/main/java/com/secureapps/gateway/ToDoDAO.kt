package com.secureapps.gateway

import androidx.room.*
import com.secureapps.entity.Task
import io.reactivex.Flowable


@Dao
interface ToDoDAO {
    @Insert
    fun insertToDo(todo: Task)

    @Delete
    fun deleteToDo(todo: Task)

    @Update
    fun updateToDo(todo: Task)

    @Query("select * from task")
    fun getTaskList(): Flowable<List<Task>>

}