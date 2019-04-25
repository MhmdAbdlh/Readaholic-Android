package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.readaholic.R;

import java.util.ArrayList;
import java.util.List;


/**
 * class Books Fragment
 * @author Hossam ahmed
 */
public class books extends Fragment {
    private View mView;
    private List<String> mCurrentlyReadingImageUrl;
    private List<String> mWantToReadImageUrl;
    private List<String> mReadImageUrl;
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
    private BooksListsAdapter2 mWantToReadAdapter;
    private BooksListsAdapter3 mCurrentlyReadingAdapter;
    public int NumberOfBooks;
    /**
     * onCreateView called when the view is created
     * @param inflater inflate the layout
     * @param container parent view
     * @param savedInstanceState bundle of saved states
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mView = inflater.inflate(R.layout.fragment_books, container, false);

         NumberOfBooks = getArguments().getInt("books-num");
        TextView BookNumber = (TextView)mView.findViewById(R.id.BookFragment_BookNumbers_TextView);
        BookNumber.setText(Integer.toString(NumberOfBooks)+" Books");




        mNotAvaliableCurrentlyReadingTextView = (TextView) mView.findViewById(R.id.BookFragment_CurrentlyReadingNotAvaliable_TextView);
        mNotAvaliableWantToReadTextView = (TextView) mView .findViewById(R.id.BookFragment_WantToReadngNotAvaliable_TextView);
        mNotAvaliableReadTextView = (TextView) mView.findViewById(R.id.BookFragment_ReadNotAvaliable_TextView);

        mNotAvaliableCurrentlyReadingTextView.setVisibility(View.INVISIBLE);
        mNotAvaliableReadTextView.setVisibility(View.INVISIBLE);
        mNotAvaliableWantToReadTextView.setVisibility(View.INVISIBLE);





        if(mCurrentlyReadingImageUrl==null)
        {
            mNotAvaliableCurrentlyReadingTextView.setVisibility(View.VISIBLE);
        }
        else {
            mCurrentlyReadingRecyclerView = (RecyclerView) mView.findViewById(R.id.BookFragment_CurrentlyReading_RecyclerView);
            //mCurrentlyReadingRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mCurrentlyReadinglayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            mCurrentlyReadinglayoutManager.setReverseLayout(true);
            mCurrentlyReadinglayoutManager.setStackFromEnd(true);
            mCurrentlyReadingRecyclerView.setLayoutManager(mCurrentlyReadinglayoutManager);

            // specify an adapter
            mCurrentlyReadingAdapter = new BooksListsAdapter3(getContext(),mCurrentlyReadingImageUrl);
            mCurrentlyReadingAdapter.notifyDataSetChanged();
            mCurrentlyReadingRecyclerView.setAdapter(mCurrentlyReadingAdapter);
        }



        if(mWantToReadImageUrl==null)
        {
            mNotAvaliableWantToReadTextView.setVisibility(View.VISIBLE);
        }
        else {
            mWantToReadRecyclerView = (RecyclerView) mView.findViewById(R.id.BookFragment_WantToRead_RecyclerView);
            //mWantToReadRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mWantToReadlayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
            mWantToReadlayoutManager.setReverseLayout(true);
            mWantToReadlayoutManager.setStackFromEnd(true);
            mWantToReadRecyclerView.setLayoutManager(mWantToReadlayoutManager);

            // specify an adapter
            mWantToReadAdapter = new BooksListsAdapter2(getContext(),mWantToReadImageUrl);

            mWantToReadRecyclerView.setAdapter(mWantToReadAdapter);
            mWantToReadAdapter.notifyDataSetChanged();
        }




        if(mReadImageUrl==null)
        {
            mNotAvaliableReadTextView.setVisibility(View.VISIBLE);
        }
        else {
            mReadRecyclerView = (RecyclerView) mView.findViewById(R.id.BookFragment_Read_RecyclerView);

            //mReadRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mReadlayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            mReadlayoutManager.setReverseLayout(true);
            mReadlayoutManager.setStackFromEnd(true);
            mReadRecyclerView.setLayoutManager(mReadlayoutManager);

            // specify an adapter
            mReadAdapter = new BooksListsAdapter(getContext(),mReadImageUrl);
            mReadRecyclerView.setAdapter(mReadAdapter);
            mReadAdapter.notifyDataSetChanged();
        }

        return mView;
    }
    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentlyReadingImageUrl = new ArrayList<>();

        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");


        mReadImageUrl = new ArrayList<>();

        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");


        mWantToReadImageUrl = new ArrayList<>();

        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");

    }
}
