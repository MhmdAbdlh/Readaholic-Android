package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * FollowingList Adapter as adapter of followings list
 * @author Hossam Ahmed
 */
public class FollowingLiastAdapter extends RecyclerView.Adapter<FollowingLiastAdapter.MyViewHolder> {
    private List<Users> mUsers;
    private Context mcontext;

    /**
     * MyViewHolder classe to hold the view elements
     * @author Hossam Ahmed
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView userImageView;
        private TextView userNameTextView;
        private TextView userBooksNumberTextView;

        /**
         * view holder constructor
         * @param v view
         */
        public MyViewHolder(View v) {
            super(v);
            userNameTextView = (TextView) v.findViewById(R.id.FollowingList_UserIName_TextView);
            userImageView =(ImageView) v.findViewById(R.id.FollowingList_UserImage_ImageView);
            userBooksNumberTextView = (TextView) v.findViewById(R.id.FollowingList_UserBooksNumber_TextView);
        }
    }

    /**
     * Adpater constructor
     * @param context context of the layout
     * @param users user object to fill the layout with their data
     */
    public FollowingLiastAdapter(Context context, List<Users> users) {
        mUsers = users;
        mcontext = context;
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
                .inflate(R.layout.following_list, parent, false);

        return new MyViewHolder(v);
    }

    /**
     * OnBindViewHolder to Replace the contents of a view (invoked by the layout manager)
     * @param myViewHolder holding the view
     * @param i position of the current view.
     */
    @Override
    public void onBindViewHolder(@NonNull FollowingLiastAdapter.MyViewHolder myViewHolder, int i) {
        if(mUsers.get(i).getmUserImageUrl() == null)
        {
            myViewHolder.userImageView.setImageResource(R.drawable.reader);
        }
        else {
           Picasso.get().load(mUsers.get(i).getmUserImageUrl()).transform(new CircleTransform()).into(myViewHolder.userImageView);
        }
        myViewHolder.userNameTextView.setText( mUsers.get(i).getmUserName());
        myViewHolder.userBooksNumberTextView.setText( mUsers.get(i).getmUsernumberOfBooks()+" Books");
        //following button todo.
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

