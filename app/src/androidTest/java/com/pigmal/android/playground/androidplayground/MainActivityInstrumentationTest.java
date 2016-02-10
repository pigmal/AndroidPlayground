package com.pigmal.android.playground.androidplayground;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void displayHelloWorld(){
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    @Test
    public void fabShowSnackbar(){
        onView(withId(R.id.fab)).perform(click());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Replace with your own action")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void alwaysSuccess(){
        assertTrue(true);
    }

//    @Test
//    public void alwaysFail(){
//        assertTrue(false);
//    }
}
