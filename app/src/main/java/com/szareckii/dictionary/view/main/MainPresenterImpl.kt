package com.szareckii.dictionary.view.main

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.datasource.DataSourceLocal
import com.szareckii.dictionary.model.datasource.DataSourceRemote
import com.szareckii.dictionary.model.repository.RepositoryImplementation
import com.szareckii.dictionary.presenter.Presenter
import com.szareckii.dictionary.rx.SchedulerProvider
import com.szareckii.dictionary.view.base.View
import io.reactivex.disposables.CompositeDisposable

class MainPresenterImpl<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribe({
                    currentView?.renderData(it)
                },{
                    currentView?.renderData(AppState.Error(it))
                })
        )
    }
}
