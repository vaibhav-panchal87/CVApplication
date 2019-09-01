package com.mvvm.cvapplication.di

import com.mvvm.cvapplication.BuildConfig
import com.mvvm.cvapplication.data.remote.APIRetrofitServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class APIServiceModule {

    @Singleton
    @Provides
    open fun provideRetrofit(
        gsonFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): APIRetrofitServices {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonFactory)
            .client(okHttpClient)
            .build().create(APIRetrofitServices::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHTTPInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(logginInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logginInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

}