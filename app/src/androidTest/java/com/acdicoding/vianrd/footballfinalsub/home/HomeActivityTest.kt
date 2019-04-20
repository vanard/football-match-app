package com.acdicoding.vianrd.footballfinalsub.home

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.id.*
import com.acdicoding.vianrd.footballfinalsub.view.HomeActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mRule = ActivityTestRule(HomeActivity::class.java)

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            InstrumentationRegistry.getTargetContext().deleteDatabase("Favorite.db")
        }
    }

    @Test
    fun homeAppTest() {
        Espresso.onView(ViewMatchers.withId(bottom_navigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(teams)).perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(rcv_team))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rcv_team)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        Espresso.onView(ViewMatchers.withId(rcv_team)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, ViewActions.click()))

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(tabs))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(viewpager))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(img_thumbnail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(add_to_favorite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Added to favorite"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

    }

}
