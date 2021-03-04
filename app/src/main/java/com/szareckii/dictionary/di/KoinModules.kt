package com.szareckii.dictionary.di

import androidx.room.Room
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.datasource.RetrofitImplementation
import com.szareckii.dictionary.model.datasource.RoomDataBaseImplementation
import com.szareckii.dictionary.model.repository.Repository
import com.szareckii.dictionary.model.repository.RepositoryImplementation
import com.szareckii.dictionary.model.repository.RepositoryImplementationLocal
import com.szareckii.dictionary.model.repository.RepositoryLocal
import com.szareckii.dictionary.room.HistoryDataBase
import com.szareckii.dictionary.view.history.HistoryInteractor
import com.szareckii.dictionary.view.history.HistoryViewModel
import com.szareckii.dictionary.view.main.MainInteractor
import com.szareckii.dictionary.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }

    single { get<HistoryDataBase>().historyDao() }
    }

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
//    viewModel { HistoryViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    viewModel { HistoryViewModel(get()) }
}