package com.example.android.readaholic.profile_and_profile_settings;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * class FollowersTab Fragment of the tabbed fragment
 * @author Hossam ahmed
 */
public class FollowersTab_Fragment extends Fragment {
     List<Users> followers;
     TextView mNotAvaliableTextView;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManger;
     FollowersListAdapter mAdapter;
     RequestQueue mRequestQueue;
     View view;
     int userId;
     int followersNum;
     TextView followersTitle;
     private static ProgressDialog mProgressDialog;
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
        view = inflater.inflate(R.layout.followerstab_fragment,container,false);
        mNotAvaliableTextView = (TextView) view.findViewById(R.id.FollowersTab_fragment_NotAvaliable_TextView);
        mNotAvaliableTextView.setVisibility(View.INVISIBLE);
        followersTitle = (TextView) view.findViewById(R.id.FollowersTab_fragment_Followers_TextView);

        Bundle bundle = getArguments();
        if( bundle == null)
            userId =0;

        else {
            userId = bundle.getInt("user-id");
            followersNum = bundle.getInt("followers-num");
        }
        ExtractFollowersArray(userId);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * function to extract followers array of users from response
     */
    private void ExtractFollowersArray(int userId)
    {
        followers = new ArrayList<>();
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String mRequestUrl;
        if(userId == 0)
        mRequestUrl = Urls.ROOT + "/api/followers?"+"token="+
                UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        else
            mRequestUrl = Urls.ROOT + "/api/followers?user_id="+Integer.toString(userId)+"&token="+
                    UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        showSimpleProgressDialog(getContext(),"Loading.....","Loading Followers And Followings",false);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject Response) {
                        Log.e("followers tab response",Response.toString());

                        JSONArray Followers = Response.optJSONArray("followers");
                        if (Followers == null) {
                            Log.e("Followers tab","followers tab followers array in json is null");
                            followers = null;
                        } else {
                            Log.e("Followers tab","followers tab followers array in json is not  null");
                            for (int i = 0; i < Followers.length(); i++) {
                                Users user = new Users();
                                user.setmUserName(Followers.optJSONObject(i).optString("name"));
                                user.setmUserId(Followers.optJSONObject(i).optInt("id"));
                                user.setmUserImageUrl(Followers.optJSONObject(i).optString("image_link"));
                                int state =Followers.optJSONObject(i).optInt("is_followed");
                                if(state==1)
                                    user.setmFollowerState(true);
                                else
                                    user.setmFollowerState(false);
                                followers.add(user);
                            }
                            UpdateList();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                followers = null;
                mRequestQueue.stop();
            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }
    private void UpdateList()
    {
        removeSimpleProgressDialog();
        if(followers.isEmpty())
        {
            mNotAvaliableTextView.setVisibility(View.VISIBLE);
        }
        else {
            followersTitle.setText(Integer.toString(followersNum) + " FOLLOWERS");
            mRecyclerView = (RecyclerView) view.findViewById(R.id.FollowersTab_fragment_FollowersList_RecyclerView);

            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView.setLayoutManager(mLayoutManger);

            // specify an adapter
            mAdapter = new FollowersListAdapter(getContext(), followers, new FollowersListAdapter.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    int userId = followers.get(position).getmUserId();
                    Intent profileIntent = new Intent(getContext(), Profile.class);
                    profileIntent.putExtra("user-idFromFollowingList",userId);
                    startActivity(profileIntent);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
