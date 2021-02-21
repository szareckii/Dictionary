package com.szareckii.dictionary.view.base

import androidx.appcompat.app.AppCompatActivity
import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

}
