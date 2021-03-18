package com.szareckii.repository

import com.szareckii.model.data.AppState
import com.szareckii.model.data.dto.DataModelDto
import com.szareckii.repository.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModelDto> {
    val searchResult = ArrayList<DataModelDto>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModelDto(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}
