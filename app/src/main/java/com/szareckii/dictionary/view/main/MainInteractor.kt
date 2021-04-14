package com.szareckii.dictionary.view.main

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.repository.Repository
import com.szareckii.dictionary.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {

        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
