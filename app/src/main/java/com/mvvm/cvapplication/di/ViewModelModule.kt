package com.mvvm.cvapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.cvapplication.cvdetail.CVDetailViewModel
import com.mvvm.cvapplication.projectHistory.ProjectHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CVDetailViewModel::class)
    protected abstract fun provideCVDetailViewModel(listViewModel: CVDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectHistoryViewModel::class)
    protected abstract fun provideProjectHistoryViewModel(listViewModel: ProjectHistoryViewModel): ViewModel
}