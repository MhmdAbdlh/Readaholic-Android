package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.TypeTextAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
public class signInTest {

    @Rule
    public ActivityTestRule<SignIn> mSignInTestRule =
            new ActivityTestRule<SignIn>(SignIn.class);

    @Test
    public void testWrongUserName(){
        Espresso.onView(withId(R.id.SignIn_userName_edittext))
                .perform(typeText("a"));
        Espresso.onView(withId(R.id.SignIn_password_edittext))
                .perform(typeText("admin"));

        Espresso.onView(withId(R.id.SignIn_signin_btn))
                .perform(click());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("Please check your email and password")));

    }


    @Test
    public void testWrongpassword(){
        Espresso.onView(withId(R.id.SignIn_userName_edittext))
                .perform(typeText("admin"));
        Espresso.onView(withId(R.id.SignIn_password_edittext))
                .perform(typeText("123456"));

        Espresso.onView(withId(R.id.SignIn_signin_btn))
                .perform(click());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("Please check your email and password")));

    }

    @Test
    public void testEmptyUsername(){
        Espresso.onView(withId(R.id.SignIn_userName_edittext))
                .perform(typeText(""));
        Espresso.onView(withId(R.id.SignIn_password_edittext))
                .perform(typeText("123456"));

        Espresso.onView(withId(R.id.SignIn_signin_btn))
                .perform(click());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("Please fill the username and password fields")));

    }

    @Test
    public void testEmptypassword(){
        Espresso.onView(withId(R.id.SignIn_userName_edittext))
                .perform(typeText("a"));
        Espresso.onView(withId(R.id.SignIn_password_edittext))
                .perform(typeText(""));

        Espresso.onView(withId(R.id.SignIn_signin_btn))
                .perform(click());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("Please fill the username and password fields")));

    }


    @Test
    public void testInvalidCharacterInUsername(){
        Espresso.onView(withId(R.id.SignIn_userName_edittext))
                .perform(typeText("ahmed moh g "));
        Espresso.onView(withId(R.id.SignIn_password_edittext))
                .perform(typeText("123456"));

        Espresso.onView(withId(R.id.SignIn_signin_btn))
                .perform(click());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("UserName or password should not contain spaces")));

    }

    @Test
    public void testInvalidCharacterInPassword(){
        Espresso.onView(withId(R.id.SignIn_userName_edittext))
                .perform(typeText("a12"));
        Espresso.onView(withId(R.id.SignIn_password_edittext))
                .perform(typeText("12 34 56"));

        Espresso.onView(withId(R.id.SignIn_signin_btn))
                .perform(click());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("UserName or password should not contain spaces")));

    }


}


