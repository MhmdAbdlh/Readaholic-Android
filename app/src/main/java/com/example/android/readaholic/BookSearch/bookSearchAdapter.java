package com.example.android.readaholic.BookSearch;

import android.content.Context;
import android.content.Intent;
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

import com.daimajia.easing.linear.Linear;
import com.example.android.readaholic.R;
import com.example.android.readaholic.books.BookPageActivity;
import com.example.android.readaholic.books.BookPageInfo;
import com.example.android.readaholic.books.Cbookdata;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.pusher.client.channel.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class bookSearchAdapter extends ArrayAdapter<bookSearchModel> {
    private Context mContext;
    public bookSearchAdapter(@NonNull Context context, List<bookSearchModel> books) {
        super(context, 0,books);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        final bookSearchModel Item = getItem(position);
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




        LinearLayout book = (LinearLayout)ListItemView.findViewById(R.id.bookTicket_Layout);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Cbookdata.INSTANCE.setBookid(Item.getBookId());
                    Intent intent = new Intent(mContext , BookPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
            }
        });

        return ListItemView;



    }
}