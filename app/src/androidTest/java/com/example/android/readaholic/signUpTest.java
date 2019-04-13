package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.TypeTextAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.inputmethod.InputMethodManager;

import com.example.android.readaholic.sign_in_up.SignIn;
import com.example.android.readaholic.sign_in_up.SignUp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class signUpTest {

    @Rule
    public ActivityTestRule<SignUp> mSignUpTestRule =
            new ActivityTestRule<SignUp>(SignUp.class);

    @Test
    public void testEmptyFields(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Please fill all fields")).check(matches(isDisplayed()));

    }
    @Test
    public void testEmptyEmail(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_city_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_Pass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_firstName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Please fill all fields")).check(matches(isDisplayed()));

    }

    @Test
    public void testEmptyPassword(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_city_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_firstName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Please fill all fields")).check(matches(isDisplayed()));

    }

    @Test
    public void testEmptyCity(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_Pass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_firstName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Please fill all fields")).check(matches(isDisplayed()));

    }

    @Test
    public void testEmptyName(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();


        Espresso.onView(withId(R.id.SignUp_city_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_Pass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Please fill all fields")).check(matches(isDisplayed()));

    }


    @Test
    public void testPasswrdsDontMatch(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_firstName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_city_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_Pass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("b"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("The two passwords you entered don't match")).check(matches(isDisplayed()));

    }


    @Test
    public void testSpacesInEmail(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a  "));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_firstName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_city_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_Pass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Email , username and password should not contain spaces")).check(matches(isDisplayed()));

    }

    @Test
    public void testSpacesInPassword(){
        int titleId = mSignUpTestRule.getActivity().getResources()
                .getIdentifier( "alertTitle", "id", "android" );
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_email_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_firstName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_lastName_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_city_edittext))
                .perform(typeText("a"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_Pass_edittext))
                .perform(typeText("a "));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_rePass_edittext))
                .perform(typeText("a "));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.SignUp_signUp_btn)).perform(click());

        Espresso.onView(withText("Email , username and password should not contain spaces")).check(matches(isDisplayed()));

    }



}


