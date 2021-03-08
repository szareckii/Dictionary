package com.szareckii.repository.repository

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.repository.datasource.DataSourceLocal
import com.szareckii.repository.room.HistoryEntity

// RepositoryImplementationLocal теперь содержит два метода, наследуется от
// RepositoryLocal и в конструктор получает инстанс DataSourceLocal
class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getHistoryEntityData(appState: AppState): HistoryEntity? {
        return dataSource.getHistoryEntityData(appState)
    }
}
