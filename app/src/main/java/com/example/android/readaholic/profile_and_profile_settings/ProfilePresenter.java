package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;

import com.android.volley.Request;
import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;

/**
 * Profile Presenter Layer.
 * @author Hossam ahmed
 */
public class ProfilePresenter {
    private Context context;
    private ProfileView profileView;
    private volleyRequestHelper volleyRequestHelper;

    /**
     * constructor of ProfilePresenter class
     * @param context view context
     * @param profileView object to view
     */
    public ProfilePresenter( Context context,ProfileView profileView)
    {
        this.context = context;
        this.profileView = profileView;
    }

    public ProfileView getProfileView() {
        return profileView;
    }

    /**
     * function to fetch data of user from response.
     * @param url String of endpoint of api
     * @return user object holding userdata
     */
    public Users fetchData(String url)
    {
        volleyRequestHelper =new volleyRequestHelper(context);
        volleyRequestHelper.JsonObjectRequest("UsersRequest",url,null, Request.Method.GET,false);
        return volleyRequestHelper.getmUser();
    }

    /**
     * Destroy the view
     */
    public void onDestroy()
    {
        profileView = null;
    }
}
