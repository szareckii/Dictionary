package com.szareckii.dictionary.presenter

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}