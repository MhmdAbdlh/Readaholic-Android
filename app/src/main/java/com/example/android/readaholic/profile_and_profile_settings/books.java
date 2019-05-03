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

import com.android.volley.RequestQueue;
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
    private RequestQueue mRequestQueue;
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
/*    private void ExtractBooks(int id)
    {
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        final String mRequestUrl;
        if(id == 0) {
            Log.e("followingsInProfile","user id is sent equal to 0");
            mRequestUrl =Urls.ROOT + "/api/following?" + "token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        }
        else

        {
       //     Log.e("followingsInProfile","user id is sent equal to  "+Integer.toString(userId));
         //   mRequestUrl =Urls.ROOT + "/api/following?user_id="+Integer.toString(userId) + "&token=" +
         //           UserInfo.sToken + "&type=" + UserInfo.sTokenType;
       //     Log.e("FollowingInProfile",mRequestUrl);
        }

        mCurrentlyReadingImageUrl = new ArrayList<>();
        mReadImageUrl = new ArrayList<>();
        mWantToReadImageUrl = new ArrayList<>();

        //progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("BooksInProfileRes",response.toString());


                JSONArray followings = response.optJSONArray("following");
                if (followings == null) {
                //    mUsers = null ;
                } else {
                    for (int i = 0; i < followings.length(); i++) {
                        String userImageUrl = null;
                        userImageUrl =(followings.optJSONObject(i).optString("image_link"));
                  //      mUsers.add(userImageUrl);
                    }
                   // UpdateList();
                }

                mRequestQueue.stop();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    //    mUsers = null;
                        Log.e("Volly ERROR","Error in volley request");

                    }
                });
        mRequestQueue.add(jsonObjectRequest);

    }
    */
    /*private void UpdateList()
    {
        //progressBar.setVisibility(View.INVISIBLE);
        if(mUsers.isEmpty())
        {
            Log.e("not available ","not available in following and followers ");
            mNotAvaliable.setVisibility(View.VISIBLE);
        }
        else {

            recyclerView = (RecyclerView) view.findViewById(R.id.FollowersFragment_FollowersList_RecyclerView);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(layoutManager);

            //       recyclerView.setHasFixedSize(true);

            // specify an adapter
            mAdapter = new FollowersAdapter(getContext(),mUsers);
            mAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(mAdapter);

        }

*/
    }

