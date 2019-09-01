package com.mvvm.cvapplication.di

import android.app.Application
import com.mvvm.cvapplication.CVApplicationClass
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        APIServiceModule::class,
        ViewModelModule::class]
)
interface CVComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: CVApplicationClass)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun apiRetrofitServiceForMocking(apiServiceModule: APIServiceModule): Builder

        fun build(): CVComponent
    }
}