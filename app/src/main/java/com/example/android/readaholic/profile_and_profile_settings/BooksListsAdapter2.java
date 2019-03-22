package com.example.android.readaholic.profile_and_profile_settings;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.readaholic.R;

import java.util.List;
/**
 * BookListsAdapter of Want To Read Books
 * @author Hossam Ahmed
 */
public class BooksListsAdapter2 extends RecyclerView.Adapter<BooksListsAdapter2.MyViewHolder> {
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
        //@BindView(R.id.WantToReadList_WantToReadBook_ImageView)
        private ImageView UserImageView;
        ViewGroup viewGroup;

        /**
         * view holder constructor
         * @param v view
         */
        public MyViewHolder(View v) {
            super(v);
           // ButterKnife.bind(v);
            UserImageView= (ImageView)v.findViewById(R.id.WantToReadList_WantToReadBook_ImageView);
            viewGroup = (ViewGroup)itemView;
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    /**
     * Adpater constructor
     * @param context context of the layout
     * @param users user object to fill the layout with their data
     */
    public BooksListsAdapter2(Context context,List<String> users) {
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
    public BooksListsAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v =  LayoutInflater.from(mcontext).inflate(R.layout.wanttoreadlist, parent, false);
        return new MyViewHolder(v);

    }

    // Replace the contents of a view (invoked by the layout manager)
    /**
     * OnBindViewHolder to Replace the contents of a view (invoked by the layout manager)
     * @param holder holding the view
     * @param position position of the current view.
     */
    @Override
    public void onBindViewHolder(BooksListsAdapter2.MyViewHolder holder, int position) {
        // - get element from my dataset at this position
        // - replace the contents of the view with that element
        holder.viewGroup.removeAllViews();

       // Picasso.get().load(mUsers.get(position)).into(holder.UserImageView);
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

