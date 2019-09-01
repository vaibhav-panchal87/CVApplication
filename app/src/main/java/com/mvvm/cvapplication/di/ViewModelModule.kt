package com.mvvm.cvapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.cvapplication.cvdetail.CVDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //TODO add View Models

    @Binds
    @IntoMap
    @ViewModelKey(CVDetailViewModel::class)
    protected abstract fun provideCVDetailViewModel(listViewModel: CVDetailViewModel): ViewModel
}