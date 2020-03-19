package com.secureapps.interactor

import com.secureapps.gateway.ToDoDAO
import io.reactivex.Flowable
import javax.inject.Inject

class DashboardInteractor @Inject constructor(val toDoGateway: ToDoDAO) {

    fun getTaskCount(startTime: String, endTime: String) : Flowable<Int>{
        return toDoGateway.getTaksCount(startTime,endTime);
    }

}