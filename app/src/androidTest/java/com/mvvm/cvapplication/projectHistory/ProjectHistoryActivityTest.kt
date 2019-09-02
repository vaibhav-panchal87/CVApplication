package com.mvvm.cvapplication.projectHistory

import android.content.Intent
import android.os.Parcelable
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
import com.mvvm.cvapplication.util.AppConstants
import com.mvvm.cvapplication.util.FakeAPIResponses
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
@LargeTest
class ProjectHistoryActivityTest {

    lateinit var cvModel: CVModel

    @get:Rule
    var projectHistoryActivityTestRule: ActivityTestRule<ProjectHistoryActivity> = object : ActivityTestRule<ProjectHistoryActivity>(ProjectHistoryActivity::class.java) {

        override fun getActivityIntent(): Intent {
            //Create Fake response and conditions ====
            val cvResponse = FakeAPIResponses.convertJsonToClass(
                    FakeAPIResponses.fakeCVJsonResponse200,
                    CVResponse::class.java
            )
            cvModel = CVModelMapper().convert(cvResponse)

            val intent = Intent()
            intent.putParcelableArrayListExtra(
                    AppConstants.PRAM_DATA,
                    cvModel.projects as ArrayList<out Parcelable>
            )
            return intent
        }
    }

    @Test
    fun checkRecyclerViewIsLoaded() {
        Espresso.onView(ViewMatchers.withId(R.id.rvProjectHistory))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

    }

    @Test
    fun checkViewModelDataIsSetAndMatches() {
        val projectList = projectHistoryActivityTestRule.activity.viewModel.projectListLiveData.value

        Assert.assertNotNull(projectList)
        Assert.assertArrayEquals(cvModel.projects.toTypedArray() ,projectList?.toTypedArray() )

    }

}