package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
 public class FollowersListAdapter extends RecyclerView.Adapter<FollowersListAdapter.MyViewHolder> {
        private ArrayList<Users> mUsers;
        private Context mcontext;
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public ImageView userImageView;
            public TextView userNameTextView;
            public TextView userBooksNumberTextView;
            public Button userFollowingStatusButton;
            public MyViewHolder(View v) {
                super(v);
                userNameTextView = (TextView) v.findViewById(R.id.FollowersList_UserIName_TextView);
                userImageView =(ImageView) v.findViewById(R.id.FollowersList_UserImage_ImageView);
                userBooksNumberTextView = (TextView) v.findViewById(R.id.FollowersList_UserBooksNumber_TextView);
                userFollowingStatusButton = (Button) v.findViewById(R.id.FollowersList_UserFollowingStatus_Button);
            }
        }

        public FollowersListAdapter(Context context, ArrayList<Users> users) {
            mUsers = users;
            mcontext = context;
        }

        @Override
        public FollowersListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            ImageView v = (ImageView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.followerslist, parent, false);

            return new MyViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(@NonNull FollowersListAdapter.MyViewHolder myViewHolder, int i) {
            if(mUsers.get(i).getmUserImageUrl() == null)
            {
             myViewHolder.userImageView.setImageResource(R.drawable.reader);
            }
            else {
                Picasso.get().load(mUsers.get(i).getmUserImageUrl()).into(myViewHolder.userImageView);
            }
            myViewHolder.userNameTextView.setText( mUsers.get(i).getmUserName());
            myViewHolder.userBooksNumberTextView.setText( mUsers.get(i).getmNumberOfFollowers()+" Books");
            //following button todo.
        }


        @Override
        public int getItemCount() {
            if(mUsers ==null)
                return 0;
            else return mUsers.size();
        }

    }
