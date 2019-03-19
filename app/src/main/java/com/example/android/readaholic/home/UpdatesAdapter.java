package com.example.android.readaholic.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.readaholic.books.BookPageActivity;

import com.example.android.readaholic.R;

import java.util.ArrayList;

public class UpdatesAdapter extends ArrayAdapter<Updates> {
    private Context activity;
    static LinearLayout likedpost;
    static TextView commentView;
    public UpdatesAdapter(Context context, ArrayList<Updates> objects) {
        super(context,0, objects);
        activity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Updates Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.update_item,parent,false);
        }

//altf ya rab
        //sha8aly kda
        TextView Name = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_name_textview);
        TextView date = (TextView) ListItemView.findViewById(R.id.UpdateActivity_updatedate_textview);
        TextView innerdate = (TextView) ListItemView.findViewById(R.id.UpdateActivity_updatedatelikedpost_textview);
        TextView Type = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_typeofupdate_textview);
        RatingBar rateBar = (RatingBar) ListItemView.findViewById(R.id.ratingBar);
        LinearLayout viewOfBook = (LinearLayout)ListItemView.findViewById(R.id.UpdateActivity_bookview_linearlayout);
        TextView bookName = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_bokkname_textview);
        TextView followerName = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_followername_textview);
        TextView review = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_reviewinrate_textview);
        TextView authorName = (TextView)ListItemView.findViewById(R.id.UpdateActivity_authorname_textview);
        TextView updateType = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_updatetype_textview);
        TextView innerUpdatetype = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_typeofupdate_textview);
        likedpost = (LinearLayout) ListItemView.findViewById(R.id.UpdatesActivity_likedpost_LinearLayout);
        final TextView likeButton = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_likebutton_textveie);
        final TextView commentButton = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_commentbutton_textveie);
        final Button wantToRead = (Button) ListItemView.findViewById(R.id.UpdateActivity_wanttoread_button);
        final ImageView image1 = (ImageView)ListItemView.findViewById(R.id.UpdatesAcrivity_downArrow_imageview);
        final ImageView image2 = (ImageView)ListItemView.findViewById(R.id.UpdatesActivity_check_imageview);
        final ImageButton image3 = (ImageButton)ListItemView.findViewById(R.id.UpdatesActivity_arrowtobookstatus_imagebutton);
        TextView numOfLike = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_numberoflikes_textview);
        TextView numOfComment = (TextView)ListItemView.findViewById(R.id.UpdatesActivity_numberofcomments_textview);
        TextView userLikedPost = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_namelikedpost_textview);
        TextView followerLikedPost = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_followernamelikedpost_textview);
        TextView likedPostType = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_typeofupdatelikedpost_textview);
        commentView = (TextView) ListItemView.findViewById(R.id.UpdatesActivity_comment_textview);
        ImageView bookImage = (ImageView) ListItemView.findViewById(R.id.UpdatesActivity_bookimage_imageview);

        Name.setText(Item.getmNameOfUser());
        date.setText(Item.getmDateOfUpdates());
        numOfLike.setText( Integer.toString(Item.getmNumOfLikes()));
        numOfComment.setText(Integer.toString(Item.getmNumOfComments()));

        //Picasso.get().load("https://images.gr-assets.com/users/1489660298p2/65993249.jpg").into(bookImage);
        commentView.setVisibility(View.GONE);
        image1.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.VISIBLE);
        wantToRead.setBackgroundResource(R.color.colorGreen);
        wantToRead.setTextColor(Color.parseColor("#F4F1EC"));

        if (Item.getmTypeOfUpdates() == 0){
            if(Item.getmRatingValue() == 0){
                Type.setText("Wrote a");
                followerName.setText("Review");
                review.setText(Item.getmReview());
                followerName.setVisibility(View.VISIBLE);
                review.setVisibility(View.VISIBLE);
                rateBar.setVisibility(View.GONE);
            }else{
                Type.setText("Rated it");
                rateBar.setVisibility(View.VISIBLE);
                followerName.setVisibility(View.GONE);
                review.setVisibility(View.GONE);
                rateBar.setRating(Item.getmRatingValue());
            }
            authorName.setText(Item.getmAuthorName());
            bookName.setText(Item.getmBookName());
            viewOfBook.setVisibility(View.VISIBLE);
            likedpost.setVisibility(View.GONE);
        }

        else if (Item.getmTypeOfUpdates() == 1){
            Type.setText(Item.getmNameofFollow());
            bookName.setText(Item.getmBookName());
            authorName.setText(Item.getmAuthorName());
            viewOfBook.setVisibility(View.VISIBLE);
            followerName.setVisibility(View.GONE);
            likedpost.setVisibility(View.GONE);
            rateBar.setVisibility(View.GONE);
            review.setVisibility(View.GONE);
        }

        else if (Item.getmTypeOfUpdates() == 3 || Item.getmTypeOfUpdates() == 4){
            likedPostType.setText("Liked");
            innerdate.setText(Item.getmDateOfUpdates());
            date.setText(Item.getmInnerDate());
            followerLikedPost.setText(Item.getmNameOfUser());
            userLikedPost.setText(Item.getmNameofFollow());
            rateBar.setVisibility(View.GONE);
            review.setVisibility(View.GONE);
            viewOfBook.setVisibility(View.VISIBLE);
            switch (Item.getmInnerUpdate()){
                case 0:
                    followerName.setVisibility(View.GONE);
                    if(Item.getmRatingValue() == 0){
                        updateType.setText("review");
                        innerUpdatetype.setText("wrote a review");
                        review.setText(Item.getmReview());
                    }else{
                        updateType.setText("rating");
                        innerUpdatetype.setText("rated it");
                        rateBar.setRating(Item.getmRatingValue());
                        rateBar.setVisibility(View.VISIBLE);
                        review.setVisibility(View.GONE);
                    }
                    bookName.setText(Item.getmBookName());
                    authorName.setText(Item.getmAuthorName());
                    break;
                case 1:
                    updateType.setText("status update");
                    innerUpdatetype.setText("Want to read");
                    bookName.setText(Item.getmBookName());
                    authorName.setText(Item.getmAuthorName());
                    followerName.setVisibility(View.GONE);
                    break;
                case 2:
                    updateType.setText("update");
                    innerUpdatetype.setText("is now following");
                    followerName.setText(Item.getmNameofFollow());
                    followerName.setVisibility(View.VISIBLE);
                    viewOfBook.setVisibility(View.GONE);
                    break;
            }
            if(Item.getmTypeOfUpdates() == 4){
                likedPostType.setText("Commented on");
                commentView.setText(Item.getmComment());
                commentView.setVisibility(View.VISIBLE);
            }
            likedpost.setVisibility(View.VISIBLE);
        }

        else if (Item.getmTypeOfUpdates() == 2){
            Type.setText("is now Following");
            followerName.setText(Item.getmNameofFollow());
            followerName.setVisibility(View.VISIBLE);
            viewOfBook.setVisibility(View.GONE);
            likedpost.setVisibility(View.GONE);
            rateBar.setVisibility(View.GONE);
            review.setVisibility(View.GONE);
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                likeButton.setText("UnLike");
            }
        });

        Name.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //go to profile
            }
        });

        wantToRead.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                wantToRead.setBackgroundResource(R.color.colorPrimary);
                wantToRead.setTextColor(Color.BLACK);
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.GONE);

            }
        });

        commentButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (Item.getmNewActivity() != 1) {
                    Intent intent = new Intent(v.getContext(), UpdatePage.class);
                    intent.putExtra("UpdateItem", Item);
                    v.getContext().startActivity(intent);
                }

            }
        });
        bookName.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent (v.getContext(), BookPageActivity.class);
                intent.putExtra("BookID",Item.getmBookId());
                v.getContext().startActivity(intent);
            }
        });
        followerName.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Item.getmTypeOfUpdates() == 0 || Item.getmTypeOfUpdates() == 4 || Item.getmTypeOfUpdates() == 3) {
                    Intent intent = new Intent(v.getContext(), UpdatePage.class);
                    intent.putExtra("UpdateItem", Item);
                    v.getContext().startActivity(intent);
                }else if(Item.getmTypeOfUpdates() == 2){
                    //go to profile and send id
                }

            }
        });
        if(Item.getmNewActivity() == 1){
            likedpost.setVisibility(View.GONE);
            commentView.setVisibility(View.GONE);
        }

        return ListItemView;
    }
}
