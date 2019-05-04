package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.R;
import com.example.android.readaholic.books.BookPageActivity;
import com.example.android.readaholic.books.Cbookdata;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.explore.BookModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * class Books Fragment
 * @author Hossam ahmed
 */
public class books extends Fragment {
    private View mView;
    private List<BookModel> mCurrentlyReadingImageUrl;
    private List<BookModel> mWantToReadImageUrl;
    private List<BookModel> mReadImageUrl;
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
         int ID = getArguments().getInt("user-id");
         NumberOfBooks = getArguments().getInt("books-num");
        TextView BookNumber = (TextView)mView.findViewById(R.id.BookFragment_BookNumbers_TextView);
        BookNumber.setText(Integer.toString(NumberOfBooks)+" Books");

        ExtractBooks(ID);


        return mView;
    }
    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        mCurrentlyReadingImageUrl = new ArrayList<>();
        mReadImageUrl = new ArrayList<>();
        mWantToReadImageUrl = new ArrayList<>();

        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mCurrentlyReadingImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");



        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");



        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
        mWantToReadImageUrl.add("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");
*/
    }
    private void ExtractBooks(int id)
    {
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        final String mRequestReadsUrl;
        final String mRequestWantsToReadUrl;
        final String mRequestCurrentlyReadingUrl;
        if(id == 0) {
            mRequestReadsUrl =Urls.getselfbooks(Integer.toString(0));
            mRequestWantsToReadUrl =Urls.getselfbooks(Integer.toString(2));
            mRequestCurrentlyReadingUrl =Urls.getselfbooks(Integer.toString(1));
        }
        else
        {
            mRequestReadsUrl =Urls.ROOT + "/api/shelf?user_id="+Integer.toString(id)+"&shelf_name="+Integer.toString(0) + "&token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
            mRequestCurrentlyReadingUrl =Urls.ROOT + "/api/shelf?user_id="+Integer.toString(id)+"&shelf_name="+Integer.toString(1) + "&token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
            mRequestWantsToReadUrl =Urls.ROOT + "/api/shelf?user_id="+Integer.toString(id)+"&shelf_name="+Integer.toString(2) + "&token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        }

        mCurrentlyReadingImageUrl = new ArrayList<>();
        mReadImageUrl = new ArrayList<>();
        mWantToReadImageUrl = new ArrayList<>();
        //progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, mRequestReadsUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("BooksReads",response.toString());
                JSONArray pages = response.optJSONArray("pages");
                if (pages == null) {
                    mReadImageUrl = null;
                } else {
                    for (int i = 0; i < pages.length(); i++) {
                        BookModel book = new BookModel();
                        book.setmImageUrl(pages.optJSONObject(i).optString("img_url"));
                        book.setmId(pages.optJSONObject(i).optInt("id"));
                        mReadImageUrl.add(book);
                    }
                    //updateLists();
                }

                mRequestQueue.stop();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mReadImageUrl = null;
                        Log.e("Volly ERROR","Error in volley request");

                    }
                });
        mRequestQueue.add(jsonObjectRequest1);

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, mRequestCurrentlyReadingUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("BooksCurrentlyReading",response.toString());
                JSONArray pages = response.optJSONArray("pages");
                if (pages == null) {
                    mCurrentlyReadingImageUrl = null ;
                } else {
                    for (int i = 0; i < pages.length(); i++) {
                        BookModel book = new BookModel();
                        book.setmImageUrl(pages.optJSONObject(i).optString("img_url"));
                        book.setmId(pages.optJSONObject(i).optInt("id"));
                        mCurrentlyReadingImageUrl.add(book);
                    }

                   // updateLists();
                }

                mRequestQueue.stop();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mCurrentlyReadingImageUrl = null;
                        Log.e("Volly ERROR","Error in volley request");

                    }
                });
        mRequestQueue.add(jsonObjectRequest2);

        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, mRequestWantsToReadUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("BooksWantToReadReading",response.toString());
                JSONArray pages = response.optJSONArray("pages");
                if (pages == null) {
                    mWantToReadImageUrl = null ;
                } else {
                    for (int i = 0; i < pages.length(); i++) {
                        BookModel book = new BookModel();
                        book.setmImageUrl(pages.optJSONObject(i).optString("img_url"));
                        book.setmId(pages.optJSONObject(i).optInt("id"));
                        mWantToReadImageUrl.add(book);
                    }
                    updateLists();
                }

                mRequestQueue.stop();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mWantToReadImageUrl = null;
                        Log.e("Volly ERROR","Error in volley request");

                    }
                });
        mRequestQueue.add(jsonObjectRequest3);

    }


    private void updateLists()
    {
        mNotAvaliableReadTextView = (TextView) mView.findViewById(R.id.BookFragment_ReadNotAvaliable_TextView);

        mNotAvaliableReadTextView.setVisibility(View.INVISIBLE);

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
            mReadAdapter = new BooksListsAdapter(getContext(), mReadImageUrl, new BooksListsAdapter.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    //Cbookdata.bookid=1
                    Log.e("ReadList","u have click on books image");
                    Cbookdata.INSTANCE.setBookid(mReadImageUrl.get(position).getmId());
                    Intent intent = new Intent(getContext(), BookPageActivity.class);
                    startActivity(intent);
                }
            });
            mReadRecyclerView.setAdapter(mReadAdapter);
            mReadAdapter.notifyDataSetChanged();
        }
        mNotAvaliableCurrentlyReadingTextView = (TextView) mView.findViewById(R.id.BookFragment_CurrentlyReadingNotAvaliable_TextView);

        mNotAvaliableCurrentlyReadingTextView.setVisibility(View.INVISIBLE);





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
            mCurrentlyReadingAdapter = new BooksListsAdapter3(getContext(), mCurrentlyReadingImageUrl, new BooksListsAdapter3.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.e("CurrentlyReadList","u have click on books image");
                    Cbookdata.INSTANCE.setBookid(mCurrentlyReadingImageUrl.get(position).getmId());
                    Intent intent = new Intent(getContext(), BookPageActivity.class);
                    startActivity(intent);
                }
            });
            mCurrentlyReadingAdapter.notifyDataSetChanged();
            mCurrentlyReadingRecyclerView.setAdapter(mCurrentlyReadingAdapter);
        }
        mNotAvaliableWantToReadTextView = (TextView) mView .findViewById(R.id.BookFragment_WantToReadngNotAvaliable_TextView);

        mNotAvaliableWantToReadTextView.setVisibility(View.INVISIBLE);






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
            mWantToReadAdapter = new BooksListsAdapter2(getContext(), mWantToReadImageUrl, new BooksListsAdapter2.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.e("WantToReadList","u have click on books image");
                    Cbookdata.INSTANCE.setBookid(mWantToReadImageUrl.get(position).getmId());
                    Intent intent = new Intent(getContext(), BookPageActivity.class);
                    startActivity(intent);
                }
            });

            mWantToReadRecyclerView.setAdapter(mWantToReadAdapter);
            mWantToReadAdapter.notifyDataSetChanged();
        }




    }






    private void UpdateList1()
    {

        mNotAvaliableReadTextView = (TextView) mView.findViewById(R.id.BookFragment_ReadNotAvaliable_TextView);

        mNotAvaliableReadTextView.setVisibility(View.INVISIBLE);

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
            mReadAdapter = new BooksListsAdapter(getContext(), mReadImageUrl, new BooksListsAdapter.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    //Cbookdata.bookid=1
                    Log.e("ReadList","u have click on books image");
                    Cbookdata.INSTANCE.setBookid(mReadImageUrl.get(position).getmId());
                    Intent intent = new Intent(getContext(), BookPageActivity.class);
                    startActivity(intent);
                }
            });
            mReadRecyclerView.setAdapter(mReadAdapter);
            mReadAdapter.notifyDataSetChanged();
        }


    }
    private void UpdateList2()
    {

        mNotAvaliableCurrentlyReadingTextView = (TextView) mView.findViewById(R.id.BookFragment_CurrentlyReadingNotAvaliable_TextView);

        mNotAvaliableCurrentlyReadingTextView.setVisibility(View.INVISIBLE);





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
            mCurrentlyReadingAdapter = new BooksListsAdapter3(getContext(), mCurrentlyReadingImageUrl, new BooksListsAdapter3.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.e("CurrentlyReadList","u have click on books image");
                    Cbookdata.INSTANCE.setBookid(mCurrentlyReadingImageUrl.get(position).getmId());
                    Intent intent = new Intent(getContext(), BookPageActivity.class);
                    startActivity(intent);
                }
            });
            mCurrentlyReadingAdapter.notifyDataSetChanged();
            mCurrentlyReadingRecyclerView.setAdapter(mCurrentlyReadingAdapter);
        }




    }
    private void UpdateList3()
    {

        mNotAvaliableWantToReadTextView = (TextView) mView .findViewById(R.id.BookFragment_WantToReadngNotAvaliable_TextView);

        mNotAvaliableWantToReadTextView.setVisibility(View.INVISIBLE);






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
            mWantToReadAdapter = new BooksListsAdapter2(getContext(), mWantToReadImageUrl, new BooksListsAdapter2.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.e("WantToReadList","u have click on books image");
                    Cbookdata.INSTANCE.setBookid(mWantToReadImageUrl.get(position).getmId());
                    Intent intent = new Intent(getContext(), BookPageActivity.class);
                    startActivity(intent);
                }
            });

            mWantToReadRecyclerView.setAdapter(mWantToReadAdapter);
            mWantToReadAdapter.notifyDataSetChanged();
        }

        TextView BookNumber = (TextView)mView.findViewById(R.id.BookFragment_BookNumbers_TextView);
        BookNumber.setText(Integer.toString(NumberOfBooks)+" Books");

    }
}
