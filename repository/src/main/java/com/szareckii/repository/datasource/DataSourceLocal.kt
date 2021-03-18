package com.szareckii.repository.datasource

import com.szareckii.model.data.AppState
import com.szareckii.repository.room.HistoryEntity

// Наследуемся от DataSource и добавляем нужный метод
interface DataSourceLocal<T> : DataSource<T> {

    suspend fun getHistoryEntityData(appState: AppState): HistoryEntity?

    suspend fun saveToDB(appState: AppState)
}