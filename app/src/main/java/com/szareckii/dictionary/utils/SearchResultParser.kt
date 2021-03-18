package com.szareckii.dictionary.utils

import com.szareckii.model.data.AppState
import com.szareckii.model.data.dto.DataModelDto
import com.szareckii.model.data.dto.MeaningsDto
import com.szareckii.model.data.userdata.DataModel
import com.szareckii.model.data.userdata.Meaning
import com.szareckii.model.data.userdata.TranslatedMeaning

fun parseOnlineSearchResults(data: AppState): AppState {
    return AppState.Success(mapResult(data, true))
}

fun mapSearchResultToResult(searchResults: List<DataModelDto>): List<DataModel> {
    return searchResults.map { searchResult ->
        var meanings: List<Meaning> = listOf()
        searchResult.meanings?.let { // Дополнительная проверка для
            // HistoryScreen, так как там сейчас не
            // отображаются значения
            meanings = it.map { meaningsDto ->
                Meaning(
                    TranslatedMeaning(meaningsDto?.translation?.translation ?: ""),
                    meaningsDto?.imageUrl ?: "",
                    meaningsDto?.transcription ?: ""
                )
            }
        }
        DataModel(searchResult.text ?: "", meanings)
    }
}

private fun mapResult(
    data: AppState,
    isOnline: Boolean
): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    data: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModel>
) {
    val dataModels: List<DataModel> = data.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(
                    DataModel(
                        searchResult.text,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(
    dataModel: DataModel,
    newDataModels: ArrayList<DataModel>
) {
    if (dataModel.text.isNotBlank() && dataModel.meanings.isNotEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        newMeanings.addAll(dataModel.meanings.filter { it.translatedMeaning.translatedMeaning.isNotBlank() })
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(
                DataModel(
                    dataModel.text,
                    newMeanings
                )
            )
        }
    }
}

fun convertMeaningsToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translatedMeaning.translatedMeaning, ", ")
        } else {
            meaning.translatedMeaning.translatedMeaning
        }
    }
    return meaningsSeparatedByComma
}
