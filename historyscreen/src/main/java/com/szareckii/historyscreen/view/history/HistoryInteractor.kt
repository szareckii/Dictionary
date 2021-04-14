package com.szareckii.historyscreen.view.history

import com.szareckii.core.viewmodel.Interactor
import com.szareckii.dictionary.utils.mapSearchResultToResult
import com.szareckii.model.data.AppState
import com.szareckii.model.data.dto.DataModelDto
import com.szareckii.repository.repository.Repository
import com.szareckii.repository.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModelDto>>,
    private val repositoryLocal: RepositoryLocal<List<DataModelDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}