
package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.readaholic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FollowersAndFollowing Fragment class to handle the fragment behaviour.
 * @author Hossam Ahmed
 */
    public class FollowersAndFollowingFragment extends Fragment {

        /**
         * The {@link android.support.v4.view.PagerAdapter} that will provide
         * fragments for each of the sections. We use a
         * {@link FragmentPagerAdapter} derivative, which will keep every
         * loaded fragment in memory.
         */
        private SectionsPagerAdapter mSectionsPagerAdapter;

        /**
         * The {@link ViewPager} that will host the section contents.
         */
        private ViewPager mViewPager;
        private int mSectionNumber = 1;

    /**
     * onCreateView called when fragment is created
     * @param inflater to inflate layout
     * @param container parent of the view
     * @param savedInstanceState bundle holding the saved state
     * @return
     */
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.followersandfollowingfragment,container,false);

            Bundle bundle = getArguments();
            if(bundle!=null)
            mSectionNumber = bundle.getInt("section_number");
            // Create the adapter that will return a fragment for each of the two
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
            if(mSectionsPagerAdapter ==null)
                Log.e("ERROR ","mSectionpagerAdapter Error");
            mSectionsPagerAdapter.AddFragment(new Followingtab_Fragment(),"Following");
            mSectionsPagerAdapter.AddFragment(new FollowersTab_Fragment(),"Followers");

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager)view.findViewById(R.id.FollowersAndFollowing_Container);

            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setCurrentItem(mSectionNumber);
            TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
            //tabLayout.setupWithViewPager(mViewPager);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



            return view;
        }



        /**
         * A placeholder fragment containing a simple view.
         */

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {
            List<Fragment> ListFragments = new ArrayList<>();
            List<String> ListTitels = new ArrayList<>();
            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            /**
             * getItem to get certain fragment with its index.
             * @param position index of fragment
             * @return fragment
             */
            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                return ListFragments.get(position);
            }

            /**
             * getcount to get number of sections in fragment
             * @return number of sections
             */
            @Override
            public int getCount() {
                // Show 2 total pages.
                return ListFragments.size();
            }

            /**
             * getPageTitile to get the section title
             * @param position index of fragment corresponding to the title list .
             * @return string as title
             */
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getPageTitle(position);
            }

            /**
             * to add fragment to the view pager.
             * @param fragment fragment object to add to list
             * @param Title title of section to add to list
             */
            public void AddFragment(Fragment fragment,String Title)
            {
                ListFragments.add(fragment);
                ListTitels.add(Title);
            }
        }

    }


