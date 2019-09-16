package com.mvvm.cvapplication.data.remote

import com.mvvm.cvapplication.data.remote.model.CVResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface APIRetrofitServices {

    @GET("/{fileName}")
    suspend fun callCVAPIRoutine(@Path("fileName") fileName: String): Response<CVResponse>

}