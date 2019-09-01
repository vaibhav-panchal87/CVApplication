package com.mvvm.cvapplication

import com.mvvm.cvapplication.data.remote.APIRetrofitServices
import com.mvvm.cvapplication.data.remote.model.CVResponse
import com.mvvm.cvapplication.di.APIServiceModule
import com.mvvm.cvapplication.di.CVComponent
import com.mvvm.cvapplication.di.DaggerCVComponent
import com.mvvm.cvapplication.util.AppConstants
import com.mvvm.cvapplication.util.FakeAPIResponses
import dagger.Module
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Converter
import retrofit2.Response

class MockCVApplicationClass : CVApplicationClass() {
    /**
     * Override dagger component creation to inject mocked API retrofit response
     * */
    override fun initDaggerComponent(): CVComponent {
        return DaggerCVComponent
            .builder()
            .application(this)
            .apiRetrofitServiceForMocking(MockApplicationModule())
            .build()
    }


    /**
     * Provide Mocked API retrofit service and response of API
     * */
    @Module
    private inner class MockApplicationModule : APIServiceModule() {

        override fun provideRetrofit(
            gsonFactory: Converter.Factory,
            okHttpClient: OkHttpClient
        ): APIRetrofitServices {
            val mock = Mockito.mock(APIRetrofitServices::class.java)
            runBlocking {
                `when`(mock.callCVAPIRoutine(AppConstants.REQ_PARAM)).thenReturn(
                    Response.success(
                        FakeAPIResponses.convertJsonToClass(
                            FakeAPIResponses.fakeCVJsonResponse200,
                            CVResponse::class.java
                        )
                    )
                )

            }
            return mock
        }

    }

}