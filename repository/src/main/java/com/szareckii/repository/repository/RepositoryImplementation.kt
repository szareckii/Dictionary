package com.szareckii.repository.repository

import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.repository.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}