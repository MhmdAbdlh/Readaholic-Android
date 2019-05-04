package com.example.android.readaholic.explore;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.example.android.readaholic.BookSearch.Search;
import com.example.android.readaholic.R;
import com.example.android.readaholic.SearchableActivity;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {

    private View parent_view;
    private RecyclerView recyclerView;
    private GenreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        parent_view = findViewById(R.id.exploreTicket_Parent);

        initComponent();
        setOnClickListeners();
    }


    /**
     * initializing the recycler grid view and adding the genre items to it
     */
    private void initComponent() {
        //initializing recycler view to be grid view
        /////////////////////////////////////////////////////////////////////
        recyclerView = (RecyclerView) findViewById(R.id.explore_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        ///////////////////////////////////////////////////////////////////////

        //adding the genre items to the recycler view
        /////////////////////////////////////////////////////////////////////
        ArrayList<GenreModel> items = new ArrayList<>() ;
        items.add(new GenreModel("Comedy" , R.drawable.ic_laugh));
        items.add(new GenreModel("Crime" , R.drawable.ic_handcuff));
        items.add(new GenreModel("Drama" , R.drawable.ic_drama));
        items.add(new GenreModel("Horror" , R.drawable.ic_zombie));
        items.add(new GenreModel("History" , R.drawable.ic_history));
        items.add(new GenreModel("Music" , R.drawable.ic_music));
        items.add(new GenreModel("Manga" , R.drawable.ic_mangaa));
        items.add(new GenreModel("Science" , R.drawable.ic_rocket));
        mAdapter = new GenreAdapter(items, this);
        recyclerView.setAdapter(mAdapter);
        ///////////////////////////////////////////////////////////////////////

    }

    private  int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void setOnClickListeners()
    {
        //search for more books click listener
        //////////////////////////////////////////////////////////
        TextView searchForBooks = (TextView)findViewById(R.id.explore_searchForBooks_textView);
        searchForBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Search.class);
                //setting the search data to false to indicate that he shouldnt check for more parameters
                intent.putExtra("search", false);
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////
        TextView searchForPeople = (TextView)findViewById(R.id.explore_searchForPeople_textView);
        searchForPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getBaseContext() , SearchableActivity.class);
               startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////
    }





}
