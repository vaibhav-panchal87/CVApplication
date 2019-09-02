package com.mvvm.cvapplication.cvdetail.model

import com.mvvm.cvapplication.util.FakeResponses
import com.mvvm.cvapplication.data.remote.model.CVResponse
import org.junit.After
import org.junit.Before
import org.junit.Test

class CVModelMapperTest {

    private lateinit var cvResponse : CVResponse

    @Before
    fun setUp() {
        cvResponse = FakeResponses.convertJsonToClass(FakeResponses.fakeCVJsonResponse200,CVResponse::class.java)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `Test if CVModel Mapper works correctly`() {
        val cvMapper = CVModelMapper()
        val cvModel = cvMapper.convert(cvResponse)

        assert(cvModel.dob.equals(cvResponse.dob)){ "Unable to map dob" }
        assert(cvModel.email.equals(cvResponse.email)){ "Unable to map Email" }
        assert(cvModel.projects.size.equals(cvResponse.projects.size)){ "Unable to map Project element" }

    }
}