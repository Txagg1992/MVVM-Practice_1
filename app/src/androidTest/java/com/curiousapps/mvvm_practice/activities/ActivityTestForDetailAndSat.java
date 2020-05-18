package com.curiousapps.mvvm_practice.activities;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.curiousapps.mvvm_practice.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityTestForDetailAndSat {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void activityTestForDetailAndSat() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.school_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.sat_button), withText("Click Here to View SAT Scores"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parent),
                                        0),
                                7)));
        appCompatButton.perform(scrollTo(), click());

        pressBack();

        pressBack();
    }
    @Test
    public void testNewRecyclerViewPositionAndOpenPhoneDialer() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.school_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.detail_school_phone), withText("718-561-0113"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parent),
                                        0),
                                3)));
        appCompatTextView.perform(scrollTo(), click());

    }

    @Test
    public void testNewRecyclerViewPositionAndOpenWebLink() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.school_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(7, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.detail_school_web),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parent),
                                        0),
                                4)));
        appCompatImageView.perform(scrollTo(), click());
    }

    @Test
    public void testNewRecyclerViewPositionAndOpenMailLink() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.school_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.detail_school_mail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parent),
                                        0),
                                5)));
        appCompatImageView.perform(scrollTo(), click());
    }

    @Test
    public void testMainActivityPhoneLink() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.text_view_school_phone), withText("718-380-6929"),
                        childAtPosition(
                                allOf(withId(R.id.communication_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                5)),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
