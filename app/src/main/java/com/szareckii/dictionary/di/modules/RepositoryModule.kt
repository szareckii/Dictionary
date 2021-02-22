package com.szareckii.dictionary.di.modules

import com.szareckii.dictionary.di.NAME_LOCAL
import com.szareckii.dictionary.di.NAME_REMOTE
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.datasource.DataSource
import com.szareckii.dictionary.model.datasource.RetrofitImplementation
import com.szareckii.dictionary.model.datasource.RoomDataBaseImplementation
import com.szareckii.dictionary.model.repository.Repository
import com.szareckii.dictionary.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
        ): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
        ): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}