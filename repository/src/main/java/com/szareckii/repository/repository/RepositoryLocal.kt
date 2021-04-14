package com.szareckii.repository.repository

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.repository.room.HistoryEntity

// Наследуемся от Repository и добавляем нужный метод
interface RepositoryLocal<T> : Repository<T> {

    suspend fun getHistoryEntityData(appState: AppState): HistoryEntity?

    suspend fun saveToDB(appState: AppState)
}