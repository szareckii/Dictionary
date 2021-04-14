package com.szareckii.dictionary.di.modules

import com.szareckii.dictionary.di.NAME_LOCAL
import com.szareckii.dictionary.di.NAME_REMOTE
import com.szareckii.dictionary.model.data.DataModel
import com.szareckii.dictionary.model.repository.Repository
import com.szareckii.dictionary.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
            @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
            @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}