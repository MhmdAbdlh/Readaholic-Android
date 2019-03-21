package com.example.android.readaholic.profile_and_profile_settings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__profile);
            getSupportFragmentManager().beginTransaction().add(R.id.ProfileLayout,new ProfileFragment(),"profileFragment").commit();

    }

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
