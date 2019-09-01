package com.mvvm.cvapplication.data

import com.mvvm.cvapplication.data.remote.APIRetrofitServices
import javax.inject.Inject


class CVRepository @Inject constructor(
    private var apiRetrofitServices: APIRetrofitServices

) {
    /**
     * @param reqParam Provide the file name to get from server
     * */
    suspend fun getCVData(reqParam: String) = apiRetrofitServices.callCVAPIRoutine(reqParam)
}