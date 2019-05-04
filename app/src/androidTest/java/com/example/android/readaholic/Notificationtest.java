package com.example.android.readaholic;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.readaholic.home.Memic;
import com.example.android.readaholic.home.NotificationFragment;

import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.android.readaholic.R.*;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Notificationtest {
    @Rule
    public ActivityTestRule<Main> activityTestRule =
            new ActivityTestRule<>(Main.class);

    private NotificationFragment fragment = null;

    @Before
    public void yourSetUPFragment() {
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }

    public Matcher<? extends Object> withItemContent(String comment) {
        checkNotNull(comment);
        return withItemContent(equalTo(comment));
    }
    @Before
    public void startFragment() {
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
        startFragment();
        activityTestRule.getActivity().findViewById(id.UpdatesActivity_loading_progbar).setVisibility(View.GONE);
        fragment.onResposeAction(response1);
        fragment.showlist();
        ListView l = (ListView) fragment.getView().findViewById(id.Notificationfragment_list_listview);
        try {
            arr = new JSONArray(response1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals((l.getAdapter().getCount()), arr.length());
    }

    @Test
    public void testlikenotifi(){
        String test1 = Memic.likednotification;
        JSONArray arr = null;
        JSONObject data = null;
        try {
            data = arr.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startFragment();
        activityTestRule.getActivity().findViewById(id.UpdatesActivity_loading_progbar).setVisibility(View.GONE);
        fragment.onResposeAction(test1);
        fragment.showlist();

        ListView l = (ListView) fragment.getView().findViewById(id.Notificationfragment_list_listview);
        View item = l.getAdapter().getView(0,null,l);
        CharSequence textinlist = ((TextView)item.findViewById(id.NotificationFragment_date_textview)).getText();
        String textinjason = "";
        try {
            textinjason = (data.getString("user_name")+" liked "+data.getString("review_user_name")+"'s review");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(textinjason,textinlist);
    }
    @Test
    public void testcommentnotifi(){
        String test1 = Memic.commentnotification;
        JSONArray arr = null;
        JSONObject data = null;
        try {
            data = arr.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startFragment();
        activityTestRule.getActivity().findViewById(id.UpdatesActivity_loading_progbar).setVisibility(View.GONE);
        fragment.onResposeAction(test1);
        fragment.showlist();

        ListView l = (ListView) fragment.getView().findViewById(id.Notificationfragment_list_listview);
        View item = l.getAdapter().getView(0,null,l);
        CharSequence textinlist = ((TextView)item.findViewById(id.NotificationFragment_date_textview)).getText();
        String textinjason = "";
        try {
            textinjason = (data.getString("user_name")+" commented on "+data.getString("review_user_name")+" review of "+data.getString("book_title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(textinjason,textinlist);
    }

    @Test
    public void testfollownotifi(){
        String test1 = Memic.follownotification;
        JSONArray arr = null;
        JSONObject data = null;
        try {
            data = arr.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startFragment();
        activityTestRule.getActivity().findViewById(id.UpdatesActivity_loading_progbar).setVisibility(View.GONE);
        fragment.onResposeAction(test1);
        fragment.showlist();

        ListView l = (ListView) fragment.getView().findViewById(id.Notificationfragment_list_listview);
        View item = l.getAdapter().getView(0,null,l);
        CharSequence textinlist = ((TextView)item.findViewById(id.NotificationFragment_date_textview)).getText();
        String textinjason = "";
        try {
            textinjason = (data.getString("user_name")+" is now following you");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(textinjason,textinlist);
    }
}
