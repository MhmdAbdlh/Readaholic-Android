package com.example.android.readaholic.profile_and_profile_settings;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class ProfileTest {

    @Rule
    public ActivityTestRule<Profile> profileActivity = new ActivityTestRule<Profile>(Profile.class);
    private Profile profile = null;
    @Before
    public void setUp() throws Exception {
    profile = profileActivity.getActivity();
    }
    @Test
    public void testID()
    {
        assertNotNull(profile);
    }
    @After
    public void tearDown() throws Exception {
    profile = null;
    }
}