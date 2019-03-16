package com.example.android.readaholic.profile_and_profile_settings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.android.readaholic.R;

public class Profile extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__profile);
            loadFragment(new books(),findViewById(R.id.Profile_Books_Fragment).getId());
            loadFragment(new Followers_fragment(),findViewById(R.id.Profile_Friends_Fragment).getId());
            loadFragment(new Updates_fragment(),findViewById(R.id.Profile_Updates_Fragment).getId());
        }


        /**
         * loadFragment function to initialize the Fragment
         * @param fragment
         * @param ID
         * to initialize the Fragment argument and replace FrameLayout with it.
         * and id to assign it to certain fragment layout.
         */
        private void loadFragment(Fragment fragment,int ID) {
            // create a FragmentManager
            FragmentManager fm = getSupportFragmentManager();
            // create a FragmentTransaction to begin the transaction and replace the Fragment
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // replace the FrameLayout with new Fragment
            fragmentTransaction.add(ID, fragment);
            fragmentTransaction.commit(); // save the changes
        }

}
