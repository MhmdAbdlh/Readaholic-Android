package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> {
    private List<String> mUsers;
    private Context mcontext;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView UserImageView;
        ViewGroup viewGroup;

        public MyViewHolder(View v) {
            super(v);
             UserImageView= (ImageView)v.findViewById(R.id.FollowersList_ReaderImage_ImageView);
            viewGroup = (ViewGroup)itemView;
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FollowersAdapter(Context context,List<String> users) {
        mUsers=users;
        mcontext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FollowersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(mcontext).inflate(R.layout.followerslist, parent, false);
        return new MyViewHolder(v);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from my dataset at this position
        // - replace the contents of the view with that element
        holder.viewGroup.removeAllViews();

       Picasso.get().load(mUsers.get(position)).transform(new CircleTransform()).into(holder.UserImageView);
        //((MyViewHolder)holder).UserImageView.setImageResource(R.drawable.reader);
        holder.viewGroup.addView(holder.UserImageView);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}

