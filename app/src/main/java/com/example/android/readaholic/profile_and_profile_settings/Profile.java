package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.readaholic.R;

/**
 * profile Activity class
 * @author Hossam Ahmed
 */
public class Profile extends AppCompatActivity {

    /**
     * function that called when activity created.
     * @param savedInstanceState bundle that have the saved state
     */
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__profile);
            getSupportFragmentManager().beginTransaction().add(R.id.ProfileLayout,new ProfileFragment(),"profileFragment").commit();

    }

    /**
     * onBackPressed that called we back button pressed .
     * to hold the fragments movements
     * when move from followers fragment to followers and following fragment and back button is
     * pressed it go back to profile .
     */
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentByTag("FollowersFragment") != null)
        {
            getSupportFragmentManager().popBackStack("FollowersToFollowersAndFollowing",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else
        super.onBackPressed();

    }
}
