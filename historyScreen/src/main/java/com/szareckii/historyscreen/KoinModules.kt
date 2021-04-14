package com.szareckii.historyscreen

import com.szareckii.historyscreen.view.history.view.HistoryInteractor
import com.szareckii.historyscreen.view.history.view.HistoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    viewModel { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}