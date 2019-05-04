package com.example.android.readaholic.BookSearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class bookSearchAdapter extends ArrayAdapter<bookSearchModel> {
    public bookSearchAdapter(@NonNull Context context, List<bookSearchModel> books) {
        super(context, 0,books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        bookSearchModel Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.bookticket,parent,false);
        }

        TextView title = (TextView)ListItemView.findViewById(R.id.bookname);
        title.setText(Item.getTitle());

        ImageView image = (ImageView)ListItemView.findViewById(R.id.bookimage);
        Picasso.get()
                .load(Item.getImage())
                .error(R.drawable.bookcover)
                .into(image);

        TextView author = (TextView)ListItemView.findViewById(R.id.authorname);
        author.setText(Item.getAuthor());

        RatingBar ratingavg = (RatingBar)ListItemView.findViewById(R.id.ratingui);
        ratingavg.setRating((float)(Item.getRatingAvg()));

        TextView numOfRatings = (TextView)ListItemView.findViewById(R.id.numbrtofratings);
        numOfRatings.setText(Item.getRatingCoung() + " ratings");

        TextView publishDate = (TextView)ListItemView.findViewById(R.id.publishedday);
        publishDate.setText("First published " + Item.getPublicationDate());




        return ListItemView;



    }
}