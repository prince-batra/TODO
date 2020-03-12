package com.secureapps.presenter

import android.util.AndroidException
import android.util.Log
import com.secureapps.entity.TODO_RESULT
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AddToDoPresenter @Inject constructor(val viewData: AddToDoViewData) {

    fun showLoading(){
        viewData.showLoading.set(true)
    }

    fun subscribeSaveToDo(observable: Observable<TODO_RESULT>) : Disposable{
        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result : TODO_RESULT ->
            if(result == TODO_RESULT.SUCCESS)
                Log.d("prince","success")
            else
                Log.d("prince","Failure")

            hideLoading()
        }
    }

    fun hideLoading(){
        viewData.showLoading.set(false)
    }
}