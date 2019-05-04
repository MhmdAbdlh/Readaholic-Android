package com.example.android.readaholic.profile_and_profile_settings;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.readaholic.R;
import com.example.android.readaholic.explore.BookModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * BookListsAdapter of Want To Read Books
 * @author Hossam Ahmed
 */
public class BooksListsAdapter2 extends RecyclerView.Adapter<BooksListsAdapter2.MyViewHolder> {
    private List<BookModel> mBooks;
    private Context mcontext;
    private customItemCLickLisenter customItemCLickLisenter;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    /**
     * MyViewHolder class to hold the view elements
     * @author Hossam Ahmed
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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


        @Override
        public void onClick(View v) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    /**
     * Adpater constructor
     * @param context context of the layout
     * @param books user object to fill the layout with their data
     */
    public BooksListsAdapter2(Context context, List<BookModel> books,customItemCLickLisenter Listener) {
        mBooks=books;
        mcontext = context;
        this.customItemCLickLisenter = Listener;
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

        final BooksListsAdapter2.MyViewHolder mViewHolder = new BooksListsAdapter2.MyViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemCLickLisenter.onItemClick(v,mViewHolder.getPosition());
            }
        });

        return mViewHolder;

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

        final AtomicBoolean loaded = new AtomicBoolean();
        Picasso.get().load(mBooks.get(position).getmImageUrl()).into(holder.UserImageView, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                loaded.set(true);
            }
        });
        if (!loaded.get()) {
            // The image was immediately available.
            holder.UserImageView.setImageResource(R.drawable.bookcover);
        }
//((MyViewHolder)holder).UserImageView.setImageResource(R.drawable.reader);
        holder.viewGroup.addView(holder.UserImageView);
    }
    /**
     * getItemsCount to get the list items number.
     * @return the size of users list
     */
    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    public interface customItemCLickLisenter
    {
        public void onItemClick(View v,int position);
    }

}

