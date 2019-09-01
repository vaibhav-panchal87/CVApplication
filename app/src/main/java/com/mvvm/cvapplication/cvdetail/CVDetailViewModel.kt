package com.mvvm.cvapplication.cvdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.cvapplication.cvdetail.model.CVModel
import com.mvvm.cvapplication.cvdetail.model.CVModelMapper
import com.mvvm.cvapplication.data.CVRepository
import com.mvvm.cvapplication.util.AppConstants
import kotlinx.coroutines.*
import javax.inject.Inject

class CVDetailViewModel @Inject constructor(
    private val repo: CVRepository,
    private val cvModelMapper: CVModelMapper
) : ViewModel() {

    // Handles progress status
    val showProgressLiveData = MutableLiveData<Boolean>()

    // Handles error status
    val showError = MutableLiveData<Boolean>()

    // Holds the data for CVModel
    val cvMutableLiveData = MutableLiveData<CVModel>()

    // Holds coroutine Job reference
    private var coroutineJob: Job? = null

    //Load data on init
    init {
        loadCVData()
    }

    /**
     * Call this function to load CV data
     * */
    fun loadCVData() {
        showProgressLiveData.value = true

        // Call repository in coroutine and update the live data variables
        coroutineJob = GlobalScope.launch(Dispatchers.IO) {
            val response = repo.getCVData(AppConstants.REQ_PARAM)
            if (response.isSuccessful && response.body() != null) {
                val cvModel = cvModelMapper.convert(response.body()!!)
                cvMutableLiveData.postValue(cvModel)
                showProgressLiveData.postValue(false)
                showError.postValue(false)
            } else {
                showProgressLiveData.postValue(false)
                showError.postValue(true)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        // Cancel job if view model gets destroyed
        coroutineJob?.cancel()
    }

}