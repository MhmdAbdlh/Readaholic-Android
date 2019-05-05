package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;

import com.example.android.readaholic.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ProfileTest {
    public String authUserShowProfileResponse="{\"id\":1,\"name\":\"test\",\"username\":\"test\",\"email\":\"test@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"female\",\"country\":\"Canada\",\"city\":\"Atawwa\",\"joined_at\":\"2019-05-03\",\"followers_count\":-2,\"following_count\":-1,\"rating_avg\":5,\"rating_count\":21,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":null,\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null}";
    public String UserShowProfileResponse="{\"id\":5,\"name\":\"Salma\",\"username\":\"Salma\",\"email\":\"Salma@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"female\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"joined_at\":\"2019-05-03\",\"followers_count\":0,\"following_count\":0,\"rating_avg\":0,\"rating_count\":0,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":\"eyJpdiI6ImV3Qjlqb2cyVjh3UG1FbHRDS0F2YWc9PSIsInZhbHVlIjoiSVQ4elQwVVZ6YzlMOStrclJaTHBoQT09IiwibWFjIjoiMDdiNTQyY2MyMzkxMzE4ZjU2ODkyMzJiNmI5NjlmOTA4ZGUzZWI1MzQ4YTQ3YjQzODhjYmJhNzEzMzEwNGVkNCJ9\",\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null,\"is_followed\":1}";

    @Rule
    public ActivityTestRule<Profile> activityTestRule =
            new ActivityTestRule<>(Profile.class);

    private ProfileFragment fragment = null;

@Test
    public void fragmentRun() {
        activityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment =startFragment();
            }
        });
    Espresso.onView(withId(R.id.ProfileActivity_UserName_TextView)).check(matches(isDisplayed()));
    }

    private ProfileFragment startFragment() {
        Profile activity = (Profile)activityTestRule.getActivity();

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragment = new ProfileFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("user-idFromFollowingList",0);
        bundle.putBoolean("followingState",true);
        fragment.setArguments(bundle);
        fragmentTransaction.add(fragment, null );
//        fragmentTransaction.commit();
        return fragment;
    }


}