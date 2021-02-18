package com.szareckii.dictionary.model.datasource

import com.szareckii.dictionary.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}