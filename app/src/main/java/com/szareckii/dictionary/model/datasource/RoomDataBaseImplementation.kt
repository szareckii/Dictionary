package com.szareckii.dictionary.model.datasource

import com.szareckii.dictionary.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

     override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}