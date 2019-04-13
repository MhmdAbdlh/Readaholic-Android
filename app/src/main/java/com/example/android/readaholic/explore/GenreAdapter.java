package com.example.android.readaholic.explore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    public ArrayList<BookModel> mBooks = new ArrayList<BookModel>();
    public Context mContext;

    public GenreAdapter(ArrayList<BookModel> mBooks, Context mContext) {
        this.mBooks = mBooks;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_card_ticket
                ,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.bookName.setText(mBooks.get(i).getmTitle().toString());
        Picasso.get()
                .load(mBooks.get(i).getmImageUrl())
                .error(R.drawable.bookcover)
                .into(viewHolder.bookImage);

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView  bookName;
        ImageView bookImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.BookCard_Image);
            bookName = itemView.findViewById(R.id.BookCard_BookName_textview);
        }
    }

}
