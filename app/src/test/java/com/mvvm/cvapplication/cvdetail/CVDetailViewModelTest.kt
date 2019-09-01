package com.mvvm.cvapplication.cvdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mvvm.cvapplication.cvdetail.model.CVModelMapper
import com.mvvm.cvapplication.data.CVRepository
import com.mvvm.cvapplication.data.ResultHandler
import com.mvvm.cvapplication.data.remote.APIRetrofitServices
import com.mvvm.cvapplication.data.remote.model.CVResponse
import com.mvvm.cvapplication.util.AppConstants
import com.mvvm.cvapplication.util.FakeResponses
import com.mvvm.cvapplication.util.customObserver
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CVDetailViewModelTest {


    private lateinit var cvDetailViewModel: CVDetailViewModel

    @Mock
    private lateinit var cvRepository: CVRepository

    @Mock
    lateinit var cvModelMapper: CVModelMapper

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //Below line makes coroutine to get executed on single thread
        Dispatchers.setMain(newSingleThreadContext("UI thread"))

    }


    @Test
    fun `Test Success case of API Call`() = runBlockingTest{
        //Create Fake response and conditions ====
        val cvResponse = FakeResponses.convertJsonToClass(
            FakeResponses.fakeCVJsonResponse200,
            CVResponse::class.java
        )
        val cvModel = CVModelMapper().convert(cvResponse)
        whenever(cvModelMapper.convert(cvResponse)).thenReturn(cvModel)
        whenever(cvRepository.getCVData(AppConstants.REQ_PARAM,cvModelMapper)).thenReturn(
            ResultHandler(cvModel,true)
        )
        //===============

        cvDetailViewModel = CVDetailViewModel(cvRepository,cvModelMapper)

        cvDetailViewModel.loadCVData()

        //Test response of one live data gets response in observer
        cvDetailViewModel.showProgressLiveData.customObserver {
            assert(cvDetailViewModel.showProgressLiveData.value == false)
            assert(cvDetailViewModel.showError.value == false)
            assert(cvDetailViewModel.cvMutableLiveData.value == cvModel)
        }

    }

    @Test
    fun `Test Failure case of API Call`() = runBlockingTest{
        //Create Fake response and conditions ====
        whenever(cvRepository.getCVData(AppConstants.REQ_PARAM,cvModelMapper)).thenReturn(
            ResultHandler(null,false)
        )

        cvDetailViewModel = CVDetailViewModel(cvRepository, cvModelMapper)
        cvDetailViewModel.loadCVData()

        cvDetailViewModel.showProgressLiveData.customObserver {
            assert(cvDetailViewModel.showProgressLiveData.value == false)
            assert(cvDetailViewModel.showError.value == true)
        }

    }


}