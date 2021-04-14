package com.szareckii.dictionary.di

import androidx.room.Room
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.repository.datasource.RetrofitImplementation
import com.szareckii.repository.datasource.RoomDataBaseImplementation
import com.szareckii.repository.repository.Repository
import com.szareckii.repository.repository.RepositoryImplementation
import com.szareckii.repository.repository.RepositoryImplementationLocal
import com.szareckii.repository.repository.RepositoryLocal
import com.szareckii.repository.room.HistoryDataBase
import com.szareckii.dictionary.view.main.MainInteractor
import com.szareckii.dictionary.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

// Объявим функцию, которая будет создавать зависимости по требованию
fun injectDependencies() = loadModules
// Ленивая инициализация создаст зависимости только тогда, когда функция будет
// вызвана
private val loadModules by lazy {
    // Функция библиотеки Koin
    loadKoinModules(listOf(application, mainScreen))
}

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
}

