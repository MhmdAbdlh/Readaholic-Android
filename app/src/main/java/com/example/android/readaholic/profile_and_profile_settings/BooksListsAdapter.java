package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BooksListsAdapter extends RecyclerView.Adapter<BooksListsAdapter.MyViewHolder> {
    private ArrayList<String> mBooks;
    private Context mcontext;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView bookImageView;

        public MyViewHolder(View v) {
            super(v);
            bookImageView= (ImageView)v;
        }
    }

    public BooksListsAdapter(Context context, ArrayList<String> Books) {
        mBooks = Books;
        mcontext = context;
    }

    @Override
    public BooksListsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        ImageView v = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currentlyreadinglist, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull BooksListsAdapter.MyViewHolder myViewHolder, int i) {
        Picasso.get().load(mBooks.get(i)).into(myViewHolder.bookImageView);
    }


    @Override
    public int getItemCount() {
        if(mBooks ==null)
            return 0;
        else return mBooks.size();
    }




}
