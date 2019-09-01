package com.mvvm.cvapplication.di

import com.mvvm.cvapplication.cvdetail.CVMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    //TODO add Activity to be injected here

    @ContributesAndroidInjector
    internal abstract fun bindCVDetailActivity(): CVMainActivity
}