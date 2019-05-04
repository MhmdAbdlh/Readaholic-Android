package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * FollowersList Adapter as adapter of followers list
 * @author Hossam Ahmed
 */
public class FollowersListAdapter extends RecyclerView.Adapter<FollowersListAdapter.MyViewHolder> {
        private List<Users> mUsers;
        private Context mcontext;
        private FollowersListAdapter.customItemCLickLisenter customItemCLickLisenter;
        private RequestQueue mRequestQueue;
        private String mRequestUrl;
        private int user_id;

    /**
     * function post the state of following of users.
     */
    public void PostFollowAndFollowing()
    {
        if(user_id != 0)
            mRequestUrl = Urls.ROOT + "/api/showProfile?"+"id="+Integer.toString(user_id)+"&token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        else
            mRequestUrl = Urls.ROOT  + "/api/showProfile?"+"token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        DiskBasedCache cache = new DiskBasedCache(mcontext.getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();

    }

    /**
     * MyViewHolder classe to hold the view elements
     * @author Hossam Ahmed
     */
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // each data item is just a string in this case
            private ImageView userImageView;
            private TextView userNameTextView;
            private TextView userBooksNumberTextView;
            private Button userFollowingStatusButton;

        @Override
        public void onClick(View v) {

        }

        /**
         * view holder constructor
         * @param v view
         */
            public MyViewHolder(View v) {
                super(v);
                userNameTextView = (TextView) v.findViewById(R.id.FollowersList_UserIName_TextView);
                userImageView =(ImageView) v.findViewById(R.id.FollowersList_UserImage_ImageView);
                userBooksNumberTextView = (TextView) v.findViewById(R.id.FollowersList_UserBooksNumber_TextView);
                userFollowingStatusButton = (Button) v.findViewById(R.id.FollowersList_UserFollowingStatus_Button);
            }
        }


    public interface customItemCLickLisenter
    {
        public void onItemClick(View v,int position);
    }

    /**
     * Adpater constructor
     * @param context context of the layout
     * @param users user object to fill the layout with their data
     * @param Listener interface of custom item click listener to hold click events on list items.
     */
        public FollowersListAdapter(Context context, List<Users> users,FollowersListAdapter.customItemCLickLisenter Listener) {
            mUsers = users;
            mcontext = context;
            this.customItemCLickLisenter = Listener;
        }


    /**
     * onCreateViewHolder to inflate the layout
     * @param parent parent view
     * @param viewType type of the view
     * @return MyViewHolder object
     */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v =  LayoutInflater.from(mcontext)
                    .inflate(R.layout.followers_list, parent, false);
            final MyViewHolder mViewHolder = new MyViewHolder(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customItemCLickLisenter.onItemClick(v,mViewHolder.getPosition());
                }
            });
            return mViewHolder;        }

    /**
     * OnBindViewHolder to Replace the contents of a view (invoked by the layout manager)
     * @param myViewHolder holding the view
     * @param i position of the current view.
     */
    @Override
        public void onBindViewHolder(@NonNull final FollowersListAdapter.MyViewHolder myViewHolder, final int i) {
        final AtomicBoolean loaded = new AtomicBoolean();
        Picasso.get().load(mUsers.get(i).getmUserImageUrl()).transform(new CircleTransform()).into(myViewHolder.userImageView, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                loaded.set(true);
            }
        });
        if (!loaded.get()) {
            // The image was immediately available.
            Picasso.get().load("https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png").
                    transform(new CircleTransform()).into(myViewHolder.userImageView);
        }

        //Picasso.get().load(mUsers.get(i).getmUserImageUrl()).transform(new CircleTransform()).into(myViewHolder.userImageView);
            myViewHolder.userNameTextView.setText( mUsers.get(i).getmUserName());
            myViewHolder.userBooksNumberTextView.setText( mUsers.get(i).getmNumberOfFollowers()+" Books");

            if(UserInfo.mIsGuest == false) {
                if (!mUsers.get(i).ismFollowerState())//the user is not  following u.
                {
                    myViewHolder.userFollowingStatusButton.setText("FOLLOW");
                    myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext, R.color.colorBlack));
                } else {
                    myViewHolder.userFollowingStatusButton.setText("FOLLOWING");
                    myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext, R.color.colorWhite));
                }


                myViewHolder.userFollowingStatusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!UserInfo.IsMemic){
                        if (mUsers.get(i).ismFollowerState())//the user is following u.
                        {
                            mUsers.get(i).setmFollowerState(false);//the user  un-follow u.
                            unfollowUser(mUsers.get(i).getmUserId());
                            myViewHolder.userFollowingStatusButton.setText("FOLLOW");
                            myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext, R.color.colorBlack));

                            /// TODO: post Request to change the list of followings
                        } else if (!mUsers.get(i).ismFollowerState()) {
                            mUsers.get(i).setmFollowerState(true);//the user follow u.
                            followUser(mUsers.get(i).getmUserId());
                            myViewHolder.userFollowingStatusButton.setText("FOLLOWING");
                            myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext, R.color.colorWhite));
                            /// TODO: post Request to change the list of followings
                        }

                    }
                        else
                            {
                                if (mUsers.get(i).ismFollowerState())//the user is following u.
                                {
                                    mUsers.get(i).setmFollowerState(false);//the user  un-follow u.
                                    myViewHolder.userFollowingStatusButton.setText("FOLLOW");
                                    myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext, R.color.colorBlack));

                                    /// TODO: post Request to change the list of followings
                                } else if (!mUsers.get(i).ismFollowerState()) {
                                    mUsers.get(i).setmFollowerState(true);//the user follow u.
                                    myViewHolder.userFollowingStatusButton.setText("FOLLOWING");
                                    myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext, R.color.colorWhite));
                                    /// TODO: post Request to change the list of followings
                                }

                            }
                    }
                });
            }
            else
                {
                    myViewHolder.userFollowingStatusButton.setVisibility(View.GONE);
                }
        }

        boolean followUser(int userId) {
            mRequestUrl = Urls.ROOT + "/api/follow?" + "user_id=" + Integer.toString(userId) + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
            Log.e("followUserUrl",mRequestUrl);
            DiskBasedCache cache = new DiskBasedCache(mcontext.getCacheDir(), 1024 * 1024);
            BasicNetwork network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
            final boolean[] status = new boolean[1];
            StringRequest stringRequest = new StringRequest(Request.Method.POST, mRequestUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject Response = null;
                    try {
                        Response = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    status[0] = Response.optBoolean("status");
                    Toast.makeText(mcontext,Response.optString("message"),Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    status[0] = false;
                }
            });
            mRequestQueue.add(stringRequest);

            return status[0];
        }
    boolean unfollowUser(int userId) {
        mRequestUrl = Urls.ROOT + "/api/unfollow?" + "user_id=" + Integer.toString(userId) + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

        DiskBasedCache cache = new DiskBasedCache(mcontext.getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        final boolean[] status = new boolean[1];
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, mRequestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject Response = null;
                try {
                    Response = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                status[0] = Response.optBoolean("status");
                Toast.makeText(mcontext,Response.optString("message"),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                status[0] = false;
            }
        });
        mRequestQueue.add(stringRequest);
        return status[0];
    }

                    /**
                     * getItemsCount to get the list items number.
                     * @return the size of users list
                     */
            @Override
        public int getItemCount() {
         return mUsers.size();
        }

    }




