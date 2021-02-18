package com.szareckii.dictionary.view.base

import com.szareckii.dictionary.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}