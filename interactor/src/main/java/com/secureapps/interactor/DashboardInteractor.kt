package com.secureapps.interactor

import com.secureapps.entity.Task
import com.secureapps.gateway.ToDoDAO
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class DashboardInteractor @Inject constructor(val toDoGateway: ToDoDAO) {

    fun getTaskCount(startTime: String, endTime: String): Flowable<Int> {
        return toDoGateway.getTaksCount(startTime, endTime);
    }

    fun getEventList(startTime: String, endTime: String): Flowable<List<Task>> {
        return toDoGateway.getTaskList(startTime, endTime);
    }

    fun getAllEventList(): Flowable<List<Task>> {
        return toDoGateway.getAllTaskList();
    }


}