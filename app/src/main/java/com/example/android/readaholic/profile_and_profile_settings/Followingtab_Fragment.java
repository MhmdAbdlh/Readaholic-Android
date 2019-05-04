package com.example.android.readaholic.profile_and_profile_settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
 * class FollowingTab Fragment of the tabbed fragment
 * @author Hossam ahmed
 */
public class Followingtab_Fragment extends Fragment {
     List<Users> mUser;
     TextView mNotAvaliableTextView;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManger;
     FollowingLiastAdapter mAdapter;
    RequestQueue mRequestQueue;
    TextView Title;
    View view;
    int userId;
    int followingNum;
    private SwipeRefreshLayout swipeView;
    private static ProgressDialog mProgressDialog;
    ProgressBar progressBar;
    String FollowingResponse ="{\"following\":[{\"id\":1,\"name\":\"test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":2,\"name\":\"ta7a\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":3,\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":4,\"name\":\"Nour\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null}],\"_start\":1,\"_end\":4,\"_total\":4}";
    String FollowingResponseAuth="{\"following\":[{\"id\":4,\"name\":\"Nour\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":3,\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":5,\"name\":\"Salma\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null}],\"_start\":1,\"_end\":3,\"_total\":3}";
    String FollowingResponse3="{\"following\":[{\"id\":4,\"name\":\"Nour\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null},{\"id\":3,\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":4,\"currently_reading\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"pages\":0},{\"id\":5,\"name\":\"Salma\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"book_id\":null,\"currently_reading\":null,\"book_image\":null,\"pages\":null}],\"_start\":1,\"_end\":3,\"_total\":3}";
    /**
     * onCreateView called when the view is created
     * @param inflater inflate the layout
     * @param container parent view
     * @param savedInstanceState bundle of saved states
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.followingtab_fragment,container,false);
        Title = (TextView)view.findViewById(R.id.Followingtab_fragment_Followings_TextView);
        progressBar=(ProgressBar)view.findViewById(R.id.FollowingTab_progressBar);
        mNotAvaliableTextView = (TextView) view.findViewById(R.id.Followingtab_fragment_NotAvaliableTextView);
        mNotAvaliableTextView.setVisibility(View.INVISIBLE);

        swipeView = (SwipeRefreshLayout)view. findViewById(R.id.swipeRefresh);

        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ExtractFollowingsArray(userId);
                            swipeView.setRefreshing(false);
                        }
                    },3000);
                }

                }
        );

            swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                    Color.RED, Color.CYAN);
            swipeView.setDistanceToTriggerSync(20);// in dips
            swipeView.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used



        Bundle bundle = getArguments();
        if(bundle == null)
            userId =0;
        else {
            userId = bundle.getInt("user-id");
            followingNum = bundle.getInt("following-num");
        }
        Log.e("followingTab",Integer.toString(userId));
        ExtractFollowingsArray(userId);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * function to extract followings array of users from response
     */
    private void ExtractFollowingsArray(int id)
    {

        mUser = new ArrayList<>();
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String mRequestUrl;
        if(id == 0)
        mRequestUrl = Urls.ROOT  + "/api/following?"+"token="+
                UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        else
            mRequestUrl = Urls.ROOT  + "/api/following?user_id="+Integer.toString(id)+"&token="+
                    UserInfo.sToken+"&type="+ UserInfo.sTokenType;
        progressBar.setVisibility(View.VISIBLE);
        //showSimpleProgressDialog(getContext(),"Loading.....","Loading Followings",false);
        if(!UserInfo.IsMemic){
            Log.e("followingTab"," no mimic");
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject Response) {
                        ExtractFollowing(Response);
                        UpdateList();

                        mRequestQueue.stop();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUser = null;
                mRequestQueue.stop();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }
        else
            {
                JSONObject Response =null;
                if(id==5)
                {
                    try {
                        Response = new JSONObject(FollowingResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else if(id==0)
                    {

                        try {
                            Response = new JSONObject(FollowingResponseAuth);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                else if(id==3)
                {
                    try {
                        Response = new JSONObject(FollowingResponse3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ExtractFollowing(Response);
                UpdateList();
            }
    }

    private void UpdateList()
    {
        progressBar.setVisibility(View.INVISIBLE);
        //removeSimpleProgressDialog();
        if(mUser.isEmpty())
        {
            mNotAvaliableTextView.setVisibility(View.VISIBLE);
        }
        else {

            Title.setText("FOLLOWING "+Integer.toString(followingNum)+" READERS");
            mRecyclerView = (RecyclerView) view.findViewById(R.id.Followingtab_fragment_FollowingsList_RecyclerView);

            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView.setLayoutManager(mLayoutManger);


            // specify an adapter
            mAdapter = new FollowingLiastAdapter(getContext(), mUser, new FollowingLiastAdapter.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                   int userId = mUser.get(position).getmUserId();
                    Intent profileIntent = new Intent(getContext(), Profile.class);
                    profileIntent.putExtra("user-idFromFollowingList",userId);
                    profileIntent.putExtra("followingState",true);
                    Log.e("Following_tab","following tab user id"+Integer.toString(userId));
                    startActivity(profileIntent);
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        }

    }

    public void ExtractFollowing(JSONObject Response)
    {
        Log.e("following tab response",Response.toString());

        JSONArray followings = Response.optJSONArray("following");
        if (followings == null) {
            Log.e("Following tab test","following tab has null following array");
            mUser = null;
        } else {
            for (int i = 0; i < followings.length(); i++) {
                Users user = new Users();
                user.setmUserName(followings.optJSONObject(i).optString("name"));
                user.setmUserId(followings.optJSONObject(i).optInt("id"));
                user.setmUserImageUrl(followings.optJSONObject(i).optString("image_link"));
                user.setmFollowerState(true);
                 mUser.add(user);
            }
        }

    }
}
