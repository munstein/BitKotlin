package com.munstein.bitkotlin

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import com.munstein.bitkotlin.main.MainActivity
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class ExampleInstrumentedTest {

    @Rule @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.munstein.bitkotlin", appContext.packageName)
    }

    @Test
    fun check_ifSpinnerValuesLoad(){
        onData(anything()).inAdapterView(withId(R.id.main_spinner_currency)).atPosition(0)
                .check(matches(withText("USD")))
    }

    @Test
    fun check_ifSpinnerValuesChange(){
        onData(anything()).inAdapterView(withId(R.id.main_spinner_currency)).atPosition(1)
                .perform(click())
        onData(anything()).inAdapterView(withId(R.id.main_spinner_currency)).atPosition(2)
                .perform(click())
        onData(anything()).inAdapterView(withId(R.id.main_spinner_currency)).atPosition(1)
                .perform(click())
        onData(anything()).inAdapterView(withId(R.id.main_spinner_currency)).atPosition(2)
                .perform(click())
    }

    @Test
    fun check_IfTextViewValueLoad() {
        onView(withId(R.id.main_txt_value)).check(matches(not(withText(""))));
    }

}
