package com.szareckii.dictionary.model.repository

import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}