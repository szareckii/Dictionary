package com.szareckii.dictionary.model.datasource

import com.szareckii.dictionary.model.data.DataModel
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}