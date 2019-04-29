package com.example.android.readaholic.profile_and_profile_settings;

import android.support.test.rule.ActivityTestRule;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android.readaholic.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class ProfileFragmentTest {
@Rule
public ActivityTestRule<Profile> profileActivityTestRule = new ActivityTestRule<Profile>(Profile.class);
private Profile profileactivity = null;
    @Before
    public void setUp() throws Exception {
        profileactivity = profileActivityTestRule.getActivity();
    }
@Test
public void NameTextView()
{
    FrameLayout frameLayout = profileactivity.findViewById(R.id.ProfileLayout);
    ProfileFragment fragment = new ProfileFragment();
    profileactivity.getSupportFragmentManager().beginTransaction().add(frameLayout.getId(),fragment);
    TextView textView = fragment.getView().findViewById(R.id.ProfileActivity_UserName_TextView);
    assertNotNull(textView);
}

    @After
    public void tearDown() throws Exception {
        profileactivity = null;
    }
}