package com.szareckii.repository.datasource

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.repository.convertDataModelSuccessToEntity
import com.szareckii.repository.mapHistoryEntityToSearchResult
import com.szareckii.repository.room.HistoryDao
import com.szareckii.repository.room.HistoryEntity

// Теперь наш локальный репозиторий работает. Передаём в конструктор
// HistoryDao (вспоминаем в модуле Koin RoomDataBaseImplementation(get())).
class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    // Возвращаем список всех слов в виде понятного для Activity
    // List<SearchResult>
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun getHistoryEntityData(appState: AppState): HistoryEntity? {
//    override suspend fun getHistoryEntityData(word: String): DataModel {
//        return mapOneHistoryEntityToSearchResult(historyDao.getDataByWord(word))
         convertDataModelSuccessToEntity(appState)?.let {
           return historyDao.getDataByWord(it.word)
        }
        return null
    }

    // Метод сохранения слова в БД. Он будет использоваться в интеракторе
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}