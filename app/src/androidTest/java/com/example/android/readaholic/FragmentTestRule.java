package com.example.android.readaholic;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.android.readaholic.home.Notification;
import com.example.android.readaholic.home.NotificationFragment;

public class FragmentTestRule<F extends Fragment> extends ActivityTestRule<Main> {

    private final Class<F> mFragmentClass;
    private F mFragment;

    public FragmentTestRule(final Class<F> fragmentClass) {
        super(Main.class, true, false);
        mFragmentClass = fragmentClass;
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Instantiate and insert the fragment into the container layout
                FragmentManager manager = FragmentTestRule.this.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                try {
                    mFragment = mFragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                transaction.replace(R.id.container, mFragment);
                transaction.commit();

            }
        });
    }
    public F getFragment(){
        return mFragment;
    }

    public FragmentManager getSupportFragmentManager() {
        return FragmentTestRule.this.getActivity().getSupportFragmentManager();
    }
}