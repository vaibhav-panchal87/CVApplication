package com.mvvm.cvapplication.projectHistory

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.mvvm.cvapplication.R
import com.mvvm.cvapplication.cvdetail.model.CVModel
import com.mvvm.cvapplication.cvdetail.model.CVModelMapper
import com.mvvm.cvapplication.data.remote.model.CVResponse
import com.mvvm.cvapplication.util.FakeAPIResponses
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ProjectHistoryActivityNullIntentTest {


    @get:Rule
    var projectHistoryActivityTestRule=ActivityTestRule(ProjectHistoryActivity::class.java)

    lateinit var cvModel: CVModel

    @Before
    fun setup() {
        //Create Fake response and conditions ====
        val cvResponse = FakeAPIResponses.convertJsonToClass(
                FakeAPIResponses.fakeCVJsonResponse200,
                CVResponse::class.java
        )
        cvModel = CVModelMapper().convert(cvResponse)
    }


    @Test
    fun checkErrorMessageGettingDisplayedOnNullData() {
        Espresso.onView(ViewMatchers.withId(R.id.txtProjectHistoryError))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

    }
}