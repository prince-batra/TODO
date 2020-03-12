package com.secureapps.interactor

import com.secureapps.entity.TODO_RESULT
import com.secureapps.entity.Task
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddToDoInteractor @Inject constructor() {


    fun saveToDoInDatabase(task: Task) : Observable<TODO_RESULT>{
        return Observable.just(TODO_RESULT.SUCCESS).subscribeOn(Schedulers.io())
    }

}