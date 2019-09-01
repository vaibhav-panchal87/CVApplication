package com.mvvm.cvapplication.data.remote

import com.mvvm.cvapplication.data.remote.model.CVResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface APIRetrofitServices {

    @GET("/raw/gist/vaibhav-panchal/f0101922738edf63274958beebaf08aa/raw/81aa24a1e26dcc18e10223a27e53d464ef611262/{fileName}?token=AAAHtdA2RJjE2t7RSOslLMa-3aRM8mEXks5dc1BHwA%3D%3D")
    suspend fun callCVAPIRoutine(@Path("fileName") fileName: String): Response<CVResponse>

}