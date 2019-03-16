package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.R;

import org.json.JSONObject;

import java.util.ArrayList;


public class Followers_fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Users> mUsers;
    private TextView mNotAvaliable ;
    private ImageLoader mImageLoader;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_followers_fragment, container, false);
        //make the TextView of not available message at first invisible.
        mNotAvaliable =(TextView)view.findViewById(R.id.NotAvaliableTextView);
        mNotAvaliable.setVisibility(View.INVISIBLE);

        String url = "http://www.mocky.io/v2/5c8c3be2360000e95b8f8473";

        JSONObject Response = HTTPRequest(url);

        TextView followersTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowersNumber_TextView);
        followersTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //making intent to go to followers and followings activity.
                Intent FollowersIntent = new Intent(getContext(),FollowersAndFollowing.class);
                // put section number of the tabs as
                FollowersIntent.putExtra("section_number",1);
                startActivity(FollowersIntent);
            }

        });
        TextView followingsTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowingNumber_TextView);
        followingsTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent FollowingsIntent = new Intent(getContext(),FollowersAndFollowing.class);
                FollowingsIntent.putExtra("section_number",0);
                startActivity(FollowingsIntent);
            }

        });


        if(mUsers==null)
        {
         mNotAvaliable.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView = (RecyclerView) view.findViewById(R.id.FollowersFragment_FollowersList_RecyclerView);

            recyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter
            mAdapter = new FollowersAdapter(mUsers);
            recyclerView.setAdapter(mAdapter);
        }

        return view;
    }

    /**
     * function to do the communication process giving url
     * @param url
     * @return JSonObject as response of the request.
     */
    private JSONObject HTTPRequest(String url)
    {
        final JSONObject[] Response = new JSONObject[1];
        final RequestQueue requestQueue;
        DiskBasedCache cache = new DiskBasedCache(getActivity().getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Response[0] = response;
                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Response[0] = null;
                requestQueue.stop();
            }
        });
        requestQueue.add(jsonObjectRequest);
        return Response[0];
    }

    /**
     * function to Extract image url from json response
     * @param Response
     * @return string contain image url
     */
    private String ExtractImageUrl(JSONObject Response)
    {
        JSONObject Followeings = Response.optJSONObject("followings");
        JSONObject User = Followeings.optJSONObject("user");
        return User.optString("image_url");
    }



}
