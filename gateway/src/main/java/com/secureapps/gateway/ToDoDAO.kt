package com.secureapps.gateway

import androidx.room.*
import com.secureapps.entity.Task
import io.reactivex.Flowable


@Dao
interface ToDoDAO {
    @Insert
    fun insertToDo(todo: Task) : Long

    @Delete
    fun deleteToDo(todo: Task)

    @Update
    fun updateToDo(todo: Task)

    @Query("select * from task")
    fun getTaskList(): Flowable<List<Task>>

    @Query("select count(*) from task where startTime between :startTime and :endTime")
    fun getTaksCount(startTime:String, endTime:String): Flowable<Int>

}