package com.example.android.readaholic.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> mFragmenList= new ArrayList<Fragment>();
    private final ArrayList<String> mFragmentname = new ArrayList<String>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmenList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentname.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentname.get(position);
    }

    public void AddFragment(Fragment fragment,String title){
        mFragmenList.add(fragment);
        mFragmentname.add(title);
    }
}
