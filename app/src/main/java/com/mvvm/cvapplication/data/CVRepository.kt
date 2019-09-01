package com.mvvm.cvapplication.data

import com.mvvm.cvapplication.cvdetail.model.CVModel
import com.mvvm.cvapplication.cvdetail.model.CVModelMapper
import com.mvvm.cvapplication.data.remote.APIRetrofitServices
import javax.inject.Inject


class CVRepository @Inject constructor(
    private var apiRetrofitServices: APIRetrofitServices

) {
    /**
     * @param reqParam Provide the file name to get from server
     * */
    suspend fun getCVData(reqParam: String, mapper: CVModelMapper): ResultHandler<CVModel> {
        return try {
            val response = apiRetrofitServices.callCVAPIRoutine(reqParam)
            if (response.isSuccessful && response.body() != null) {
                val cvModel = mapper.convert(response.body()!!)
                ResultHandler(cvModel, true)
            } else {
                ResultHandler(null, false)
            }
        } catch (ex: Exception) {
            ResultHandler(null, false)
        }
    }
}