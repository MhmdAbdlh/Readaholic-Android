package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.readaholic.settings.Settings;
import com.example.android.readaholic.sign_in_up.SignIn;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class settingsTest {

    @Rule
    public ActivityTestRule<Settings> settingsActivityTestRule =
            new ActivityTestRule<Settings>(Settings.class);

    @Test
    public void testUserName(){

        Espresso.onView(withId(R.id.Settings_userNameContent_TextView))
                .check(matches(withText("ahmed nassar")));
    }


    @Test
    public void testBirthDay(){

        Espresso.onView(withId(R.id.Settings_birthDayContent_TextView))
                .check(matches(withText("1/1/1990")));

    }

    @Test
    public void testLocation(){
        Espresso.onView(withId(R.id.Settings_locationContent_TextView))
                .check(matches(withText("Egypt")));

    }

}


