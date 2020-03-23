package com.secureapps.gateway

import androidx.room.*
import com.secureapps.entity.Task
import io.reactivex.Flowable
import io.reactivex.Observable


@Dao
interface ToDoDAO {
    @Insert
    fun insertToDo(todo: Task) : Long

    @Delete
    fun deleteToDo(todo: Task)

    @Update
    fun updateToDo(todo: Task)

    @Query("select * from task where startTime between :startTime and :endTime order by LOWER(startTime) ASC")
    fun getTaskList(startTime:String, endTime:String): Flowable<List<Task>>

    @Query("select count(*) from task where startTime between :startTime and :endTime")
    fun getTaksCount(startTime:String, endTime:String): Flowable<Int>

    @Query("select * from task")
    fun getAllTaskList(): Flowable<List<Task>>

}