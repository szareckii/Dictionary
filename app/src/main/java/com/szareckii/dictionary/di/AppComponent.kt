package com.szareckii.dictionary.di

import android.app.Application
import com.szareckii.dictionary.di.modules.ActivityModule
import com.szareckii.dictionary.di.modules.InteractorModule
import com.szareckii.dictionary.di.modules.RepositoryModule
import com.szareckii.dictionary.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)

@Singleton
interface AppComponent {
    // Этот билдер мы вызовем из класса DictionaryApp, который наследует Application
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    // Наш кастомный Application
    fun inject(englishVocabularyApp: DictionaryApp)

//    fun inject(activity: MainActivity)

}