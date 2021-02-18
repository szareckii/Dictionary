package com.szareckii.dictionary.view.main

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.repository.Repository
import com.szareckii.dictionary.presenter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {

        if(word.isEmpty()) return Observable.error(IllegalStateException("Words can't be null"))

        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
