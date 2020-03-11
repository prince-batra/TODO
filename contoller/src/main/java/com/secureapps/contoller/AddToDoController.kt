package com.secureapps.contoller

import com.secureapps.interactor.AddToDoInteractor
import com.secureapps.presenter.AddToDoPresenter
import javax.inject.Inject

class AddToDoController @Inject constructor(
    private val addToDoInteractor: AddToDoInteractor,
    private val addToDoPresenter: AddToDoPresenter
) {
}