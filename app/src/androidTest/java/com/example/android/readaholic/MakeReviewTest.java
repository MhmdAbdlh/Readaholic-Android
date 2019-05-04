package com.example.android.readaholic;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.readaholic.books.Editreview;
import com.example.android.readaholic.books.ReviewActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MakeReviewTest {

    @Rule
    public ActivityTestRule<Editreview> activityTestRule =
            new ActivityTestRule<>(Editreview.class);

    @Test
    public void testemptyReive(){
        onView(withId(R.id.writerreview))
                .perform(typeText(""));

        onView(withId(R.id.savereview))
                .perform(click());
        Editreview activity = activityTestRule.getActivity();
        onView(withText("Please write something first")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

    }
    @Test
    public void testzerorating(){
        onView(withId(R.id.writerreview))
                .perform(typeText("good book"));

        onView(withId(R.id.savereview))
                .perform(click());
        Editreview activity = activityTestRule.getActivity();
        onView(withText("You have to rate at at least with one star")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

    }




}


