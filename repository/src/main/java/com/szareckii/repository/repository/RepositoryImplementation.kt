package com.szareckii.repository.repository

import com.szareckii.model.data.dto.DataModelDto
import com.szareckii.repository.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModelDto>>) :
    Repository<List<DataModelDto>> {

    override suspend fun getData(word: String): List<DataModelDto> {
        return dataSource.getData(word)
    }
}