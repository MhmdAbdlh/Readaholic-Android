package com.example.android.readaholic.explore;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.profile_and_profile_settings.books;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenreList extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private ScrollView mMainLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genre_list, container, false);
        /*************************************Getting views -> open*******************************/
        mRecyclerView = (RecyclerView)view.findViewById(R.id.GenreFragment_RecyclerView);
        mProgressbar = (ProgressBar)view.findViewById(R.id.GenreFragment_progressBar);
        mMainLayout = (ScrollView)view.findViewById(R.id.GenreFragment_scrollView);
        /*************************************Getting views -> close******************************/

        //initializing recycler view
        initRecyclerView();
        //make a search request for books with a particular genre
        genreRequest();

        return view;
    }
    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()
                                ,LinearLayoutManager.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(layoutManager);

        //GenreAdapter adapter = new GenreAdapter()
    }

    private void genreRequest() {
        Loading();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.myjson.com/bins/17ffb8";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    showLayout();
                    ArrayList books = parseGenreResponse(response);

                    GenreAdapter adapter = new GenreAdapter(books,getContext());
                    mRecyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showLayout();
                //showErrorMessage("Error in Connection");
            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



    private ArrayList<BookModel> parseGenreResponse(String response) {

        ArrayList<BookModel> books = new ArrayList<>();
        try {

            JSONObject root = new JSONObject(response);
            JSONArray booksArray = root.getJSONArray("books");


            for(int i = 0; i < booksArray.length() ; i++) {
               String title = booksArray.getJSONObject(i).getString("book_title");
               String isbn = booksArray.getJSONObject(i).getString("isbn");
               String imageUrl = booksArray.getJSONObject(i).getString("image_url");

               books.add(new BookModel(isbn,title,imageUrl));
            }

        } catch (Exception e) {
            return null;
        }

        return books;
    }


    //region UI controlling
    /**
     * showing the progress bar to indicate that request is in progress
     */
    private void Loading()
    {
        mProgressbar.setVisibility(View.VISIBLE);

        mMainLayout.setVisibility(View.GONE);

    }

    /**
     * showing the original layout
     */
    private void showLayout() {
        mProgressbar.setVisibility(View.GONE);

        mMainLayout.setVisibility(View.VISIBLE);
    }


    //endregion


}
