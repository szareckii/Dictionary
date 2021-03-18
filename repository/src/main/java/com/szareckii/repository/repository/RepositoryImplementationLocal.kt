package com.szareckii.repository.repository

import com.szareckii.model.data.AppState
import com.szareckii.model.data.dto.DataModelDto
import com.szareckii.repository.datasource.DataSourceLocal
import com.szareckii.repository.room.HistoryEntity

// RepositoryImplementationLocal теперь содержит два метода, наследуется от
// RepositoryLocal и в конструктор получает инстанс DataSourceLocal
class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModelDto>>) :
    RepositoryLocal<List<DataModelDto>> {

    override suspend fun getData(word: String): List<DataModelDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getHistoryEntityData(appState: AppState): HistoryEntity? {
        return dataSource.getHistoryEntityData(appState)
    }
}
