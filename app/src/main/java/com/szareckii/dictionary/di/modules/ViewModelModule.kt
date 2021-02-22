package com.szareckii.dictionary.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.szareckii.dictionary.di.ViewModelKey
import com.szareckii.dictionary.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// Модуль послужит источником коллекции ViewModel’ей для фабрики:
// - мы используем этот модуль для создания ViewModel
// - мы предоставляем ключ для каждой новой ViewModel при помощи класса
// ViewModelKey, созданного выше;
// - и уже в Activity мы используем фабрику для создания нужной нам ViewModel
@Module
internal abstract class ViewModelModule {

    // Фабрика
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Этот метод просто говорит Dagger’у: помести эту модель в список (map) моделей,
    // используя аннотацию @IntoMap, где в качестве ключа будет класс MainViewModel::class
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}