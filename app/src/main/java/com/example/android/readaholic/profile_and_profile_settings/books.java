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
import android.widget.FrameLayout;
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
import com.example.android.readaholic.myshelves.ShelvesFragment;

import org.json.JSONArray;
import org.json.JSONException;
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
    public int NumberOfBooks=0;
    private RequestQueue mRequestQueue;
    private FrameLayout BookFragment_SeeMore_FrameLayout;
    private String ReadsBooksResponseAuth="{\"status\":\"success\",\"pages\":[{\"book_id\":3,\"title\":\"Once & Future\",\"id\":3,\"isbn\":9780316449274,\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":7,\"ratings_count\":7,\"author_id\":3},{\"book_id\":2,\"title\":\"Sherwood\",\"id\":2,\"isbn\":9780062422330,\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":19,\"ratings_count\":19,\"author_id\":2}]}";
    private String WantsToReadBooksResponseAuth="{\"status\":\"success\",\"pages\":[{\"book_id\":1,\"title\":\"The Bird King\",\"id\":1,\"isbn\":9780802129031,\"img_url\":\"https:\\/\\/i5.walmartimages.com\\/asr\\/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\",\"reviews_count\":9,\"ratings_count\":9,\"author_id\":1}]}";
    private String CurrentlyReadingBooksResponseAuth="{\"status\":\"failed, no returned results for the input\",\"pages\":[]}";
    private String ReadsBooksResponse="{\"status\":\"success\",\"pages\":[{\"book_id\":1,\"title\":\"The Bird King\",\"id\":1,\"isbn\":9780802129031,\"img_url\":\"https:\\/\\/i5.walmartimages.com\\/asr\\/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\",\"reviews_count\":9,\"ratings_count\":9,\"author_id\":1}]}";
    private String WantsToReadBooksResponse="{\"status\":\"success\",\"pages\":[{\"book_id\":3,\"title\":\"Once & Future\",\"id\":3,\"isbn\":9780316449274,\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":7,\"ratings_count\":7,\"author_id\":3},{\"book_id\":2,\"title\":\"Sherwood\",\"id\":2,\"isbn\":9780062422330,\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":19,\"ratings_count\":19,\"author_id\":2}]}";
    private String CurrentlyReadingBooksResponse="{\"status\":\"failed, no returned results for the input\",\"pages\":[]}";
    private String ReadsBooksResponse3="{\"status\":\"success\",\"pages\":[{\"book_id\":3,\"title\":\"Once & Future\",\"id\":3,\"isbn\":9780316449274,\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":7,\"ratings_count\":7,\"author_id\":3},{\"book_id\":2,\"title\":\"Sherwood\",\"id\":2,\"isbn\":9780062422330,\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":19,\"ratings_count\":19,\"author_id\":2}]}";
    private String WantsToReadBooksResponse3="{\"status\":\"success\",\"pages\":[{\"book_id\":3,\"title\":\"Once & Future\",\"id\":3,\"isbn\":9780316449274,\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":7,\"ratings_count\":7,\"author_id\":3},{\"book_id\":2,\"title\":\"Sherwood\",\"id\":2,\"isbn\":9780062422330,\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":19,\"ratings_count\":19,\"author_id\":2}]}";
    private String CurrentlyReadingBooksResponse3="{\"status\":\"failed, no returned results for the input\",\"pages\":[]}";
    private int user_id;
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
         user_id= getArguments().getInt("user-id");
         NumberOfBooks = getArguments().getInt("books-num");
        TextView BookNumber = (TextView)mView.findViewById(R.id.BookFragment_BookNumbers_TextView);
        BookNumber.setText(Integer.toString(NumberOfBooks)+" Books");
        BookFragment_SeeMore_FrameLayout =(FrameLayout) mView.findViewById(R.id.BookFragment_SeeMore_FrameLayout);
        if(UserInfo.mIsGuest==true)
            BookFragment_SeeMore_FrameLayout.setVisibility(View.GONE);
        else
            {
                BookFragment_SeeMore_FrameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment ShelvesFragment = new ShelvesFragment();
                        Bundle bundle=new Bundle();
                        UserInfo.USER_ID = user_id;
                        ShelvesFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.ProfileLayout,
                                ShelvesFragment,"ShelvesFragment").addToBackStack("FromProfileToShelves").commit();

                    }
                });
            }
        ExtractBooks(user_id);
        //updateLists();
//        UpdateList1();
//        UpdateList2();
//        UpdateList3();
      return mView;
    }
    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*mCurrentlyReadingImageUrl = new ArrayList<>();
        mReadImageUrl = new ArrayList<>();
        mWantToReadImageUrl = new ArrayList<>();
        BookModel bookModel = new BookModel();
        bookModel.setmImageUrl("https://images.gr-assets.com/users/1507144891p3/7004371.jpg");

        mCurrentlyReadingImageUrl.add(bookModel);
        mCurrentlyReadingImageUrl.add(bookModel);
        mCurrentlyReadingImageUrl.add(bookModel);
        mCurrentlyReadingImageUrl.add(bookModel);
        mCurrentlyReadingImageUrl.add(bookModel);
        mCurrentlyReadingImageUrl.add(bookModel);
        mCurrentlyReadingImageUrl.add(bookModel);


        mReadImageUrl.add(bookModel);
        mReadImageUrl.add(bookModel);
        mReadImageUrl.add(bookModel);
        mReadImageUrl.add(bookModel);
        mReadImageUrl.add(bookModel);
        mReadImageUrl.add(bookModel);



        mWantToReadImageUrl.add(bookModel);
        mWantToReadImageUrl.add(bookModel);
        mWantToReadImageUrl.add(bookModel);
        mWantToReadImageUrl.add(bookModel);
        mWantToReadImageUrl.add(bookModel);
        mWantToReadImageUrl.add(bookModel);
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
        if(user_id == 0) {
             mRequestReadsUrl =Urls.getselfbooks(Integer.toString(0));
            mRequestWantsToReadUrl =Urls.getselfbooks(Integer.toString(2));
            mRequestCurrentlyReadingUrl =Urls.getselfbooks(Integer.toString(1));
        }
        else
        {
            mRequestReadsUrl =Urls.ROOT + "/api/shelf?user_id="+Integer.toString(user_id)+"&shelf_name="+Integer.toString(0) + "&token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
            mRequestCurrentlyReadingUrl =Urls.ROOT + "/api/shelf?user_id="+Integer.toString(user_id)+"&shelf_name="+Integer.toString(1) + "&token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
            mRequestWantsToReadUrl =Urls.ROOT + "/api/shelf?user_id="+Integer.toString(user_id)+"&shelf_name="+Integer.toString(2) + "&token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        }

        mCurrentlyReadingImageUrl = new ArrayList<>();
        mReadImageUrl = new ArrayList<>();
        mWantToReadImageUrl = new ArrayList<>();
        //progressBar.setVisibility(View.VISIBLE);
        if(!UserInfo.IsMemic){
            JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, mRequestReadsUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ExtractReads(response);
                    //updateLists();
                    UpdateList1();
                    Log.e("UpdateList1","IN");

                    mRequestQueue.stop();

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mReadImageUrl = null;
                            Log.e("Volly ERROR", "Error in volley request");

                        }
                    });
            mRequestQueue.add(jsonObjectRequest1);

            JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, mRequestCurrentlyReadingUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ExtractCurrentlyReading(response);
                    //updateLists();
                    UpdateList2();
                    Log.e("UpdateList2","IN");
                      mRequestQueue.stop();

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mCurrentlyReadingImageUrl = null;
                            Log.e("Volly ERROR", "Error in volley request");

                        }
                    });
            mRequestQueue.add(jsonObjectRequest2);

            JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.GET, mRequestWantsToReadUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ExtractWantToRead(response);
                    //updateLists();
                    UpdateList3();
                    Log.e("UpdateList3","IN");
                    mRequestQueue.stop();

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mWantToReadImageUrl = null;
                            Log.e("Volly ERROR", "Error in volley request");

                        }
                    });
            mRequestQueue.add(jsonObjectRequest3);
        }
        else
            {
                JSONObject response1 = null,response2=null,response3=null;

                if(id==0)
                {
                    try {
                         response1 = new JSONObject(ReadsBooksResponseAuth);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                         response2 = new JSONObject(CurrentlyReadingBooksResponseAuth);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                         response3 = new JSONObject(WantsToReadBooksResponseAuth);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else if(id == 5)
                    {
                        try {
                            response1 = new JSONObject(ReadsBooksResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                             response2 = new JSONObject(CurrentlyReadingBooksResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                             response3 = new JSONObject(WantsToReadBooksResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                else
                {
                    try {
                        response1 = new JSONObject(ReadsBooksResponse3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        response2 = new JSONObject(CurrentlyReadingBooksResponse3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        response3 = new JSONObject(WantsToReadBooksResponse3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ExtractReads(response1);
                UpdateList1();
                ExtractCurrentlyReading(response2);
                UpdateList2();
                ExtractWantToRead(response3);
                UpdateList3();
            }
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
            Log.e("updateList1","mReads not null");
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


    }
public void ExtractReads(JSONObject response)
{
    if(response ==null)
        mReadImageUrl=null;
    else {
        Log.e("BooksReads", response.toString());

        //if (response.optString("status") == "success") {
            Log.e("ExtractReads", "success");
            JSONArray pages = null;
            try {
                pages = response.getJSONArray("pages");
            } catch (JSONException e) {
                pages=null;
            }
            if (pages == null) {
                mReadImageUrl = null;
            } else {
                for (int i = 0; i < pages.length(); i++) {
                    BookModel book = new BookModel();
                    book.setmImageUrl(pages.optJSONObject(i).optString("img_url"));
                    book.setmId(pages.optJSONObject(i).optInt("id"));
                    mReadImageUrl.add(book);
                }
                NumberOfBooks+=pages.length();
            }
        //}
         //else if (response.optString("status") == "failed, no returned results for the input")
           // mReadImageUrl = null;
    }
}
    public void ExtractWantToRead(JSONObject response)
    {
        if(response ==null)
            mWantToReadImageUrl=null;
        else {
            Log.e("BooksWantToReadReading", response.toString());
            //if (response.optString("status") == "success") {
                Log.e("ExtractWantToRead", "success");
                JSONArray pages = null;
                try {
                    pages = response.getJSONArray("pages");
                } catch (JSONException e) {
                    pages=null;
                }
                if (pages == null) {
                    mWantToReadImageUrl = null;
                } else {
                    for (int i = 0; i < pages.length(); i++) {
                        BookModel book = new BookModel();
                        book.setmImageUrl(pages.optJSONObject(i).optString("img_url"));
                        book.setmId(pages.optJSONObject(i).optInt("id"));
                        mWantToReadImageUrl.add(book);
                    }
                    NumberOfBooks+=pages.length();
                }
            //} else if (response.optString("status") == "failed, no returned results for the input") {
              //  mWantToReadImageUrl = null;
            //}
        }
    }

    public void ExtractCurrentlyReading(JSONObject response)
    {
        if(response ==null)
            mCurrentlyReadingImageUrl=null;
        else {
            Log.e("BooksCurrentlyReading", response.toString());
            //if (response.optString("status") == "success") {
                Log.e("ExtractCurrentlyReading", "success");
                JSONArray pages = null;
                try {
                    pages = response.getJSONArray("pages");
                } catch (JSONException e) {
                    pages=null;
                }
                if (pages == null) {
                    mCurrentlyReadingImageUrl = null;
                } else {
                    for (int i = 0; i < pages.length(); i++) {
                        BookModel book = new BookModel();
                        book.setmImageUrl(pages.optJSONObject(i).optString("img_url"));
                        book.setmId(pages.optJSONObject(i).optInt("id"));
                        mCurrentlyReadingImageUrl.add(book);
                    }
                    NumberOfBooks+=pages.length();

                }
            //} else if (response.optString("status") == "failed, no returned results for the input") {
              //  mCurrentlyReadingImageUrl = null;
            //}

        }
    }

}
