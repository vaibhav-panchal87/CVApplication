package com.mvvm.cvapplication.di

import com.mvvm.cvapplication.cvdetail.CVMainActivity
import com.mvvm.cvapplication.projectHistory.ProjectHistoryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindCVDetailActivity(): CVMainActivity

    @ContributesAndroidInjector
    internal abstract fun bindProjectHistoryActivity(): ProjectHistoryActivity
}