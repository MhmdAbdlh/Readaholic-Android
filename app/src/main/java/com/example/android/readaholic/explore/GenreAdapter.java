package com.example.android.readaholic.explore;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.readaholic.BookSearch.Search;
import com.example.android.readaholic.R;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    public ArrayList<GenreModel> mGenres = new ArrayList<GenreModel>();
    public Context mContext;

    public GenreAdapter(ArrayList<GenreModel> mGenres, Context mContext) {
        this.mGenres = mGenres;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.explore_ticket,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.title.setText(mGenres.get(i).getmGenreName().toString());
        viewHolder.image.setImageResource(mGenres.get(i).getmGenrePic());

        viewHolder.mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when any genre is clicked I the clicked genre should be searched
                Intent intent = new Intent(mContext, Search.class);  //getting Search activity intent
                //setting the search data to true to indicate that he should search and check for more parameters
                intent.putExtra("search", true);
                //the key he will search for
                intent.putExtra("searchKey" , mGenres.get(i).getmGenreName().toString());
                //setting search type to Genre
                intent.putExtra("searchType" , "Genre");
                mContext.startActivity(intent); //starting the activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView image;
        LinearLayout mParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.exploreTicket_title);
            image = itemView.findViewById(R.id.exploreTicket_image);
            mParentLayout = itemView.findViewById(R.id.exploreTicket_Parent);
        }
    }
}