package com.szareckii.dictionary.di

import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.datasource.RetrofitImplementation
import com.szareckii.dictionary.model.datasource.RoomDataBaseImplementation
import com.szareckii.dictionary.model.repository.Repository
import com.szareckii.dictionary.model.repository.RepositoryImplementation
import com.szareckii.dictionary.view.main.MainInteractor
import com.szareckii.dictionary.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
        single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(
            RetrofitImplementation()
        ) }
        single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(
            RoomDataBaseImplementation()
        ) }
    }

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    viewModel { MainViewModel(get()) }
}