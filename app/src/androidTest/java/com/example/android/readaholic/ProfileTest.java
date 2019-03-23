package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.example.android.readaholic.profile_and_profile_settings.Profile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ProfileTest {

        @Rule
        public ActivityTestRule<Profile> mProfileTestRule =
                new ActivityTestRule<Profile>(Profile.class);

        @Before
        public void setup()
        {
                Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), Profile.class);
                mProfileTestRule.launchActivity(MY_ACTIVITY_INTENT);

        }

        @Test
        public void notCorrectUserNameTextDisplayed() {
                onView(withId(R.id.ProfileActivity_UserName_TextView)).check(matches(withText("Ahmed")));
        }

        @Test
        public void CorrectUserNameTextDisplayed() {
                onView(withId(R.id.ProfileActivity_UserName_TextView)).check(matches(withText("Hossam Ahmed")));
        }

        @Test
        public void ProfilePicIsExist() {
                onView(withId(R.id.profileActivity_ProfilePic_ImageView)).check(matches((isDisplayed())));
        }

        @Test
        public void NotCorrectFormatUserNameTest()
        {
                Fragment fragment = new Fragment();
                mProfileTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.ProfileLayout, fragment).commit();

                onView(withId(R.id.ProfileActivity_UserName_TextView)).check(matches(withHint("234235")));
        }

        @Test
        public void bookNumberTest()
        {
                Fragment fragment = new Fragment();
                mProfileTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.ProfileLayout, fragment).commit();

                onView(withId(R.id.ProfileActivity_UserBooksNumber_TextView)).check(matches(withText("7 Books")));

        }
        @Test
        public void NotCorrectBookNumberTest()
        {
                Fragment fragment = new Fragment();
                mProfileTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.ProfileLayout, fragment).commit();

                onView(withId(R.id.ProfileActivity_UserBooksNumber_TextView)).check(matches(withHint("--213")));

        }



}
