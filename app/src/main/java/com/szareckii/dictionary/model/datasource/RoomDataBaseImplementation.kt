package com.szareckii.dictionary.model.datasource

import com.szareckii.dictionary.model.data.AppState
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.room.HistoryDao
import com.szareckii.dictionary.room.HistoryEntity
import com.szareckii.dictionary.utils.convertDataModelSuccessToEntity
import com.szareckii.dictionary.utils.mapHistoryEntityToSearchResult

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