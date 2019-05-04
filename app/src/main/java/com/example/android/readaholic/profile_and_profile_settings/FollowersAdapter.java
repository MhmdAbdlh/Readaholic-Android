package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * FollowersList Adapter as adapter of followers list
 * @author Hossam Ahmed
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> {
    private List<String> mUsers;
    private Context mcontext;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    /**
     * MyViewHolder class to hold the view elements
     * @author Hossam Ahmed
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView UserImageView;
        ViewGroup viewGroup;
        /**
         * view holder constructor
         * @param v view
         */

        public MyViewHolder(View v) {
            super(v);
             UserImageView= (ImageView)v.findViewById(R.id.FollowersList_ReaderImage_ImageView);
            viewGroup = (ViewGroup)itemView;
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)

    /**
     * Adpater constructor
     * @param context context of the layout
     * @param users user object to fill the layout with their data
     */
    public FollowersAdapter(Context context,List<String> users) {
        mUsers=users;
        mcontext = context;
    }

    // Create new views (invoked by the layout manager)

    /**
     * onCreateViewHolder to inflate the layout
     * @param parent parent view
     * @param viewType type of the view
     * @return MyViewHolder object
     */

    @Override
    public FollowersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(mcontext).inflate(R.layout.followerslist, parent, false);
        return new MyViewHolder(v);

    }

    // Replace the contents of a view (invoked by the layout manager)

    /**
     * OnBindViewHolder to Replace the contents of a view (invoked by the layout manager)
     * @param holder holding the view
     * @param position position of the current view.
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from my dataset at this position
        // - replace the contents of the view with that element
        holder.viewGroup.removeAllViews();
        final AtomicBoolean loaded = new AtomicBoolean();
        Picasso.get().load(mUsers.get(position)).transform(new CircleTransform()).into(holder.UserImageView, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                loaded.set(true);
            }
        });
        if (!loaded.get()) {
            // The image was immediately available.
            Picasso.get().load("https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png").
                    transform(new CircleTransform()).into(holder.UserImageView);
        }

        //Picasso.get().load(mUsers.get(position)).transform(new CircleTransform()).into(holder.UserImageView);
        //((MyViewHolder)holder).UserImageView.setImageResource(R.drawable.reader);
        holder.viewGroup.addView(holder.UserImageView);
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

