package com.example.android.readaholic;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

import com.example.android.readaholic.home.HomeFragment;
import com.example.android.readaholic.home.Memic;
import com.example.android.readaholic.home.NotificationFragment;

import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Notificationtest {
    @Rule
    public ActivityTestRule<Main> activityTestRule =
            new ActivityTestRule<>(Main.class);

    private NotificationFragment fragment = null;


    public void yourSetUPFragment() {
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }

    private Matcher<? extends Object> withItemContent(String comment) {
        checkNotNull(comment);
        return withItemContent(equalTo(comment));
    }
    private void startFragment(NotificationFragment homeFragment) {
        fragment = new NotificationFragment();
        FragmentManager fragmentManager = activityTestRule.getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null );
        fragmentTransaction.replace(R.id.Main_fragmentLayout,
                fragment).commit();
    }
    @Test
    public void testlist(){
        String response1 = Memic.getNotification(1);
        JSONArray arr = null;
        startFragment(fragment);
        activityTestRule.getActivity().findViewById(R.id.UpdatesActivity_loading_progbar).setVisibility(View.GONE);
        fragment.onResposeAction(response1);
        fragment.showlist();
        ListView l = (ListView) fragment.getView().findViewById(R.id.Notificationfragment_list_listview);
        try {
            arr = new JSONArray(response1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals((l.getAdapter().getCount()), arr.length());
    }
}
