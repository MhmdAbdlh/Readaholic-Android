package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.android.volley.toolbox.NetworkImageView;
import com.example.android.readaholic.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Followers_fragment extends Fragment {

    private RecyclerView recyclerView;
    private FollowersAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mUsers;
    private TextView mNotAvaliable ;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_followers_fragment, container, false);
        //make the TextView of not available message at first invisible.
        mNotAvaliable =(TextView)view.findViewById(R.id.NotAvaliableTextView);
        mNotAvaliable.setVisibility(View.INVISIBLE);

        String url = "https://api.myjson.com/bins/fjksu";
        HTTPRequest(url);



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
            layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter
            mAdapter = new FollowersAdapter(view.getContext(),mUsers);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }


    /**
     * function to do the communication process giving url
     * @param url string url to determine the endpoint.
     *
     */
    private void HTTPRequest(String url)
    {

        DiskBasedCache cache = new DiskBasedCache(getActivity().getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ExtractUser(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly ERROR","Error in volley request");

            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }

    /**
     * function to Extract data of user from json response
     * @param Response json object to root tree that contain the user data
     *
     */
    private void ExtractUser(JSONObject Response)
    {

        JSONObject GoodReadsResponse = Response.optJSONObject("GoodreadsResponse");
        JSONArray User = GoodReadsResponse.optJSONArray("user");
        for(int i=0;i<User.length();i++)
        {
            mUsers.set(i,User.optJSONObject(i).optString("image_url"));
            Log.e("FollowersFragment",mUsers.get(i));
        }

    }

}
