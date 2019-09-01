package com.mvvm.cvapplication

import android.app.Activity
import android.app.Application
import com.mvvm.cvapplication.di.CVComponent
import com.mvvm.cvapplication.di.DaggerCVComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class CVApplicationClass : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent().inject(this)
    }

    open fun initDaggerComponent(): CVComponent {
        return DaggerCVComponent
            .builder()
            .application(this)
            .build()

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}