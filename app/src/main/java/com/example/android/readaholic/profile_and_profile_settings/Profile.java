package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.readaholic.R;
import com.example.android.readaholic.SearchableActivity;

/**
 * profile Activity class
 * @author Hossam Ahmed
 */
public class Profile extends AppCompatActivity {

    SearchView searchView;
    /**
     * function that called when activity created.
     * @param savedInstanceState bundle that have the saved state
     */
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);
        searchView = (SearchView) findViewById(R.id.Profile_searchView);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.Profile_toolbar);
        setSupportActionBar(myToolbar);


        Bundle bundle;
        bundle = getIntent().getExtras();
        int fromMenu = bundle.getInt("FromMenu");
        //if (fromMenu!=1) {
            int user_id = bundle.getInt("user-idFromFollowingList");
            boolean followngState = bundle.getBoolean("followingState");

            Fragment profile = new ProfileFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("user-id", user_id);
            if (followngState)
                bundle2.putInt("followingState", 1);
            else
                bundle2.putInt("followingState", 0);
            profile.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().add(R.id.ProfileLayout, profile, "profileFragment").commit();

        }
        /*else
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragmentLayout,
                        new FollowersAndFollowingFragment(),"FollowersAndFollowings").addToBackStack("MainToFollowersAndFollowings").commit();
            }
    }*/

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
/*        searchView = (SearchView)findViewById(R.id.Profile_searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
                Intent intent =new Intent(getBaseContext(), SearchableActivity.class);
                startActivity(intent);

            }
        });        //materialSearchView.setMenuItem(item);
  */
    return true;
    }

    /**
     * function to handle the behaviour of each item in options menu.
     * @param item menu item which is selected
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                //start search dialog
                //boolean result =super.onSearchRequested();
                Intent intent =new Intent(getBaseContext(), SearchableActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

