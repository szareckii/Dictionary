package com.szareckii.dictionary.view.main

import com.szareckii.model.data.AppState
import com.szareckii.repository.repository.Repository
import com.szareckii.repository.repository.RepositoryLocal
import com.szareckii.core.viewmodel.Interactor
import com.szareckii.dictionary.utils.mapSearchResultToResult
import com.szareckii.model.data.dto.DataModelDto

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModelDto>>,
    private val repositoryLocal: RepositoryLocal<List<DataModelDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        // Теперь полученное слово мы сохраняем в БД. Сделать это нужно именно
        // здесь, в соответствии с принципами чистой архитектуры: интерактор
        // обращается к репозиторию
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}