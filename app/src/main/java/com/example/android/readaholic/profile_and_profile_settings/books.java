package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.readaholic.R;

import java.util.ArrayList;


public class books extends Fragment {
    private View mView;
    private ArrayList<String> mCurrentlyReadingImageUrl;
    private ArrayList<String> mWantToReadImageUrl;
    private ArrayList<String> mReadImageUrl;
    private TextView mNotAvaliableCurrentlyReadingTextView;
    private TextView mNotAvaliableWantToReadTextView;
    private TextView mNotAvaliableReadTextView;
    private RecyclerView mCurrentlyReadingRecyclerView;
    private RecyclerView mWantToReadRecyclerView;
    private RecyclerView mReadRecyclerView;
    private LinearLayoutManager mCurrentlyReadinglayoutManager;
    private LinearLayoutManager mWantToReadlayoutManager;
    private LinearLayoutManager mReadlayoutManager;
    private BooksListsAdapter mReadAdapter;
    private BooksListsAdapter mWantToReadAdapter;
    private BooksListsAdapter mCurrentlyReadingAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mView = inflater.inflate(R.layout.fragment_books, container, false);

        mNotAvaliableCurrentlyReadingTextView = (TextView) mView.findViewById(R.id.BookFragment_CurrentlyReadingNotAvaliable_TextView);
        mNotAvaliableWantToReadTextView = (TextView) mView .findViewById(R.id.BookFragment_WantToReadngNotAvaliable_TextView);
        mNotAvaliableReadTextView = (TextView) mView.findViewById(R.id.BookFragment_ReadNotAvaliable_TextView);

        mNotAvaliableCurrentlyReadingTextView.setVisibility(View.INVISIBLE);
        mNotAvaliableReadTextView.setVisibility(View.INVISIBLE);
        mNotAvaliableWantToReadTextView.setVisibility(View.INVISIBLE);

        /*if(mCurrentlyReadingImageUrl==null)
        {
            mNotAvaliableCurrentlyReadingTextView.setVisibility(View.VISIBLE);
        }
        else {
            mCurrentlyReadingRecyclerView = (RecyclerView) mView.findViewById(R.id.BookFragment_CurrentlyReading_RecyclerView);
            mCurrentlyReadingRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mCurrentlyReadinglayoutManager = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);

            mCurrentlyReadingRecyclerView.setLayoutManager(mCurrentlyReadinglayoutManager);

            // specify an adapter
            mCurrentlyReadingAdapter = new BooksListsAdapter(mView.getContext(),mCurrentlyReadingImageUrl);
            mCurrentlyReadingRecyclerView.setAdapter(mCurrentlyReadingAdapter);
            mCurrentlyReadingAdapter.notifyDataSetChanged();
        }



        if(mWantToReadImageUrl==null)
        {
            mNotAvaliableWantToReadTextView.setVisibility(View.VISIBLE);
        }
        else {
            mWantToReadRecyclerView = (RecyclerView) mView.findViewById(R.id.BookFragment_WantToRead_RecyclerView);
            mWantToReadRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mWantToReadlayoutManager = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            mWantToReadRecyclerView.setLayoutManager(mCurrentlyReadinglayoutManager);

            // specify an adapter
            mWantToReadAdapter = new BooksListsAdapter(mView.getContext(),mWantToReadImageUrl);

            mWantToReadRecyclerView.setAdapter(mWantToReadAdapter);
            mWantToReadAdapter.notifyDataSetChanged();
        }




        if(mReadImageUrl==null)
        {
            mNotAvaliableReadTextView.setVisibility(View.VISIBLE);
        }
        else {
            mReadRecyclerView = (RecyclerView) mView.findViewById(R.id.BookFragment_Read_RecyclerView);

            mReadRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mReadlayoutManager = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            mReadRecyclerView.setLayoutManager(mReadlayoutManager);

            // specify an adapter
            mReadAdapter = new BooksListsAdapter(mView.getContext(),mReadImageUrl);
            mReadRecyclerView.setAdapter(mReadAdapter);
            mReadAdapter.notifyDataSetChanged();
        }

*/

        return mView;
    }




}
