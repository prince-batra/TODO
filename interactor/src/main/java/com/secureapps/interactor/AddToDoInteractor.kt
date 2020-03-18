package com.secureapps.interactor

import com.secureapps.entity.TODO_RESULT
import com.secureapps.entity.Task
import com.secureapps.gateway.ToDoDAO
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddToDoInteractor @Inject constructor(val toDoGateway: ToDoDAO) {


    fun saveToDoInDatabase(task: Task) : Observable<Long>{
        return Observable.fromCallable<Long> {
            return@fromCallable toDoGateway.insertToDo(task)
        }.subscribeOn(Schedulers.io())

//        val returnValue: Long  = toDoGateway.insertToDo(task)
//        return Observable.just(TODO_RESULT.SUCCESS).subscribeOn(Schedulers.io())
    }

}