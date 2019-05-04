package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.example.android.readaholic.settings.edit_Password.password;
import com.example.android.readaholic.sign_in_up.SignIn;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class testChangePassword {

    @Rule
    public ActivityTestRule<password> passwordActivityTestRule =
            new ActivityTestRule<password>(password.class);

    @Test
    public void testMismatchNewPassword(){
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_CurrentPass_EditTex))
                .perform(typeText("admin"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_newPassword_editText))
                .perform(typeText("123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_confirmPassword_editText))
                .perform(typeText("123456"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_savePassword_button))
                .perform(click());

        Espresso.onView(withText("Your password and confirmation password do not match")).check(matches(isDisplayed()));

    }


    @Test
    public void testEmptySpaceInPassword(){
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_CurrentPass_EditTex))
                .perform(typeText("adm in"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_newPassword_editText))
                .perform(typeText("123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_confirmPassword_editText))
                .perform(typeText("123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_savePassword_button))
                .perform(click());


        Espresso.onView(withText("password should not contain spaces")).check(matches(isDisplayed()));

    }

    @Test
    public void testEmptyPassword(){
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.Password_CurrentPass_EditTex))
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_newPassword_editText))
                .perform(typeText("123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_confirmPassword_editText))
                .perform(typeText("123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.Password_savePassword_button))
                .perform(click());


        Espresso.onView(withText("Please fill all fields")).check(matches(isDisplayed()));

    }




}

