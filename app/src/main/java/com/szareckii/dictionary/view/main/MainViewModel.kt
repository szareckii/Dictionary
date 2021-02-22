package com.szareckii.dictionary.view.main

import androidx.lifecycle.LiveData
import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.datasource.DataSourceLocal
import com.szareckii.dictionary.model.datasource.DataSourceRemote
import com.szareckii.dictionary.model.repository.RepositoryImplementation
import com.szareckii.dictionary.utils.parseSearchResults
import com.szareckii.dictionary.viewmodel.BaseViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe{ doOnSubscribe() }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.value = AppState.Loading(null) }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = parseSearchResults(state)
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

}