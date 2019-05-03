package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import com.example.android.readaholic.books.ReviewActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
@RunWith(AndroidJUnit4.class)
public class ReviewTest {

    @Rule
    public ActivityTestRule<ReviewActivity> activityTestRule =
            new ActivityTestRule<>(ReviewActivity.class);

    @Test
    public void testemptyComment(){
        onView(withId(R.id.writercomment))
                .perform(typeText(""));

        onView(withId(R.id.sendcommentbtn))
                .perform(click());
        ReviewActivity activity = activityTestRule.getActivity();
        onView(withText("Please write something first")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

    }

    @Test
    public void testfaildComment(){
        onView(withId(R.id.writercomment))
                .perform(typeText("good book "));

        onView(withId(R.id.sendcommentbtn))
                .perform(click());
        ReviewActivity activity = activityTestRule.getActivity();

        onView(withText("Something went wrong with the server")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

    }







}


