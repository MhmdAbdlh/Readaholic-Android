package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

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
            Picasso.get().load(mUsers.get(i).getmUserImageUrl()).transform(new CircleTransform()).into(myViewHolder.userImageView);
            myViewHolder.userNameTextView.setText( mUsers.get(i).getmUserName());
            myViewHolder.userBooksNumberTextView.setText( mUsers.get(i).getmNumberOfFollowers()+" Books");
            if(!mUsers.get(i).ismFollowerState())//the user is not  following u.
                {
                    myViewHolder.userFollowingStatusButton.setText("FOLLOW");
                    myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext,R.color.colorBlack));
                }
                else {
                myViewHolder.userFollowingStatusButton.setText("FOLLOWING");
                myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext,R.color.colorWhite));
                }
            myViewHolder.userFollowingStatusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mUsers.get(i).ismFollowerState())//the user is following u.
                    {
                        mUsers.get(i).setmFollowerState(false);//the user  un-follow u.
                        myViewHolder.userFollowingStatusButton.setText("FOLLOW");
                        myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext,R.color.colorBlack));

                        /// TODO: post Request to change the list of followings
                    }
                    else if(!mUsers.get(i).ismFollowerState())
                    {
                        mUsers.get(i).setmFollowerState(true);//the user follow u.
                        myViewHolder.userFollowingStatusButton.setText("FOLLOWING");
                        myViewHolder.userFollowingStatusButton.setTextColor(ContextCompat.getColor(mcontext,R.color.colorWhite));
                        /// TODO: post Request to change the list of followings
                    }

                }
            });

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

