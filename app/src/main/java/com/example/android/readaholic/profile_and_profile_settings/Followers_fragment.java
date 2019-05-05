package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * class FollowersTab Fragment of the tabbed fragment
 * @author Hossam ahmed
 */
public class Followers_fragment extends Fragment {

     RecyclerView recyclerView;
     FollowersAdapter mAdapter;
     RecyclerView.LayoutManager layoutManager;
     List<Users> mUsers;
     TextView mNotAvaliable ;
     RequestQueue mRequestQueue;
     View view;
     int userId;
     int FollowingNumber;
    int FollowersNumber;
    ProgressBar progressBar;
     String FollowesResponseAuth = "{\"following\":[{\"id\":1,\"name\":\"test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":2,\"name\":\"ta7a\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":3,\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":4,\"name\":\"Nour\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null}],\"_start\":1,\"_end\":4,\"_total\":4}";
    String FollowesResponse="{\"following\":[{\"id\":4,\"name\":\"Nour\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":3,\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":5,\"name\":\"Salma\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null}],\"_start\":1,\"_end\":3,\"_total\":3}";
    String FollowesResponse3="{\"following\":[{\"id\":4,\"name\":\"Nour\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":3,\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":5,\"name\":\"Salma\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null}],\"_start\":1,\"_end\":3,\"_total\":3}";


    /**
     * onCreateView called when the view is created
     * @param inflater inflate the layout
     * @param container parent view
     * @param savedInstanceState bundle of saved states
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_followers_fragment, container, false);
        //make the TextView of not available message at first invisible.
        progressBar=(ProgressBar)view.findViewById(R.id.FollowersFragment_progressBar);
        mNotAvaliable =(TextView)view.findViewById(R.id.NotAvaliableTextView);
        mNotAvaliable.setVisibility(View.INVISIBLE);


        Bundle bundle = getArguments();
        userId = bundle.getInt("user-id");
        FollowingNumber = bundle.getInt("following-num");
        FollowersNumber = bundle.getInt("followers-num");

        Log.e("FollowersFragment","followers: "+Integer.toString(FollowersNumber)+" followings: "+Integer.toString(FollowingNumber));





        TextView followersTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowersNumber_TextView);
        followersTextView.setText(Integer.toString(FollowersNumber)+" FOLLOWERS");
        followersTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment FollowersAndFollowing = new FollowersAndFollowingFragment();
                Bundle bundle =new Bundle();
                bundle.putInt("section_number",1);
                bundle.putInt("user-id",userId);
                bundle.putInt("following-num",FollowingNumber);
                bundle.putInt("followers-num",FollowersNumber);
                FollowersAndFollowing.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ProfileLayout,FollowersAndFollowing,"FollowersFragment").addToBackStack("FollowersToFollowersAndFollowing").commit();
            }

        });
        TextView followingsTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowingNumber_TextView);
        followingsTextView.setText(Integer.toString(FollowingNumber)+" FOLLOWINGS");
        followingsTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment FollowersAndFollowing = new FollowersAndFollowingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("section_number", 0);
                bundle.putInt("user-id",userId);
                bundle.putInt("following-num",FollowingNumber);
                bundle.putInt("followers-num",FollowersNumber);
                FollowersAndFollowing.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ProfileLayout, FollowersAndFollowing,"FollowersFragment").addToBackStack("FollowersToFollowersAndFollowing").commit();
            }
        });
        ExtractFollowings(userId);
        return view;
    }

    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }



    /**
     * function to do the communication process
     */
    private void ExtractFollowings(int id)
    {
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        final String mRequestUrl;
        if(userId == 0) {
            Log.e("followingsInProfile","user id is sent equal to 0");
            mRequestUrl =Urls.ROOT + "/api/following?" + "token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        }
        else

            {
                Log.e("followingsInProfile","user id is sent equal to  "+Integer.toString(userId));
                mRequestUrl =Urls.ROOT + "/api/following?user_id="+Integer.toString(userId) + "&token=" +
                        UserInfo.sToken + "&type=" + UserInfo.sTokenType;
                Log.e("FollowingInProfile",mRequestUrl);
            }

        mUsers = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        if(!UserInfo.IsMemic) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ExtractFollowers(response);
                    UpdateList();

                    mRequestQueue.stop();

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mUsers = null;
                            Log.e("Volly ERROR", "Error in volley request");

                        }
                    });
            mRequestQueue.add(jsonObjectRequest);
        }
        else
            {
                JSONObject response =null;
                if(id==0) {

                    try {
                        response = new JSONObject(FollowesResponseAuth);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(id==5)
                    {

                        try {
                            response = new JSONObject(FollowesResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                else
                {
                    try {
                        response = new JSONObject(FollowesResponse3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ExtractFollowers(response);
                UpdateList();
            }
    }
private void UpdateList()
{
    progressBar.setVisibility(View.INVISIBLE);
    if(mUsers.isEmpty())
    {
        Log.e("not available ","not available in following and followers ");
        mNotAvaliable.setVisibility(View.VISIBLE);
    }
    else {

        recyclerView = (RecyclerView) view.findViewById(R.id.FollowersFragment_FollowersList_RecyclerView);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //       recyclerView.setHasFixedSize(true);

        // specify an adapter
        mAdapter = new FollowersAdapter(getContext(), mUsers, new FollowersAdapter.customItemCLickLisenter() {
            @Override
            public void onItemClick(View v, int position) {
                int userId = mUsers.get(position).getmUserId();
                Intent profileIntent = new Intent(getContext(), Profile.class);
                profileIntent.putExtra("user-idFromFollowingList",userId);
                profileIntent.putExtra("followingState",true);
                Log.e("Following_tab","following tab user id"+Integer.toString(userId));
                startActivity(profileIntent);
            }
        });
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

    }


}
public void ExtractFollowers(JSONObject response)
{
    Log.d("FollowingInProfile", "user id is "+Integer.toString(userId));
    Log.e("FollowingInProfileRes",response.toString());


    JSONArray followings = response.optJSONArray("following");
    if (followings == null) {
        mUsers = null ;
    } else {
        for (int i = 0; i < followings.length(); i++) {
            Users user = new Users();
            user.setmUserImageUrl(followings.optJSONObject(i).optString("image_link"));
            user.setmUserId(followings.optJSONObject(i).optInt("id"));
            mUsers.add(user);
        }
    }

}

}
