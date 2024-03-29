package com.mvvm.cvapplication.data

import com.mvvm.cvapplication.cvdetail.model.CVModelMapper
import com.mvvm.cvapplication.data.remote.APIRetrofitServices
import com.mvvm.cvapplication.data.remote.model.CVResponse
import com.mvvm.cvapplication.util.FakeResponses
import com.mvvm.cvapplication.util.FakeResponses.fakeCVJsonResponse200
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response


class CVRepositoryTest {

    //== Mock API retrofit services
    @Mock
    lateinit var apiRetrofitServices: APIRetrofitServices

    private lateinit var cvRepository: CVRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        //Init Repository with fake services
        cvRepository = CVRepository(
            apiRetrofitServices
        )

    }

    @Test
    fun `Test API Success Case with its contents`() = runBlocking {
        //Create Fake response and conditions ====
        val cvResponse = FakeResponses.convertJsonToClass(
            fakeCVJsonResponse200,
            CVResponse::class.java
        )
        val cvModel = CVModelMapper().convert(cvResponse)
        whenever(apiRetrofitServices.callCVAPIRoutine("")).thenReturn(Response.success(cvResponse))
        //=====================

        val resultHandler = cvRepository.getCVData("",CVModelMapper())

        Assert.assertNotNull(resultHandler)
        Assert.assertNotNull(resultHandler.response)
        assert(resultHandler.isSuccess)
        assert(resultHandler.response== cvModel)
    }

    @Test
    fun `Test API Exceptione`() = runBlocking {
        //Create Fake response and conditions ====
        whenever(apiRetrofitServices.callCVAPIRoutine("")).thenReturn(
            Response.error(
                404,
                ResponseBody.create(MediaType.get("application/json"), "")
            )
        )

        val resultHandler = cvRepository.getCVData("", CVModelMapper())

        Assert.assertNotNull(resultHandler)
        assert(!resultHandler.isSuccess)
        assert(resultHandler.response == null)
    }

}

