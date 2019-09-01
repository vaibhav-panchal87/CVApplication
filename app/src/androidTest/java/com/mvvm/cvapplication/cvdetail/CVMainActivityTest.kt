package com.mvvm.cvapplication.cvdetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.mvvm.cvapplication.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CVMainActivityTest {

    @get:Rule
    var cvMainActivityTestRule = ActivityTestRule(CVMainActivity::class.java)

    @Test
    fun buttonClick_CheckNavigationToProjectScreen(){

        onView(withId(R.id.svCVMainActivity)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.btnCVProjectHistory)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.btnCVProjectHistory)).perform(ViewActions.click())
        onView(withId(R.id.rootViewProjectHistory)).check(matches(
            isDisplayed()))
    }

    @Test
    fun checkViewsAreLoaded(){
        onView(withId(R.id.svCVMainActivity)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVAddress)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVEmail)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVFullName)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVGender)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVPhoneNumber)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVSkills)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.txtCVSummery)).check(matches(isCompletelyDisplayed()))

    }

    @Test
    fun checkIfEmailAndButtonIsClickable(){
        onView(withId(R.id.txtCVEmail)).check(matches(isClickable()))
        onView(withId(R.id.btnCVProjectHistory)).check(matches(isClickable()))
    }

    @Test
    fun checkIfDataArePopulatedCorrectly(){
        val cvModel = cvMainActivityTestRule.activity.viewModel.cvMutableLiveData.value
        onView(withId(R.id.txtCVEmail)).check(matches(withText(cvModel?.email)))
        onView(withId(R.id.txtCVAddress)).check(matches(withText(cvModel?.address)))
        onView(withId(R.id.txtCVGender)).check(matches(withText(cvModel?.gender)))
        onView(withId(R.id.txtCVPhoneNumber)).check(matches(withText(cvModel?.phoneNumber)))
        onView(withId(R.id.txtCVSkills)).check(matches(withText(cvModel?.skills)))
        onView(withId(R.id.txtCVSummery)).check(matches(withText(cvModel?.summary)))
    }
    
}