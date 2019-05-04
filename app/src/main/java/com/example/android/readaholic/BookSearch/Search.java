package com.example.android.readaholic.BookSearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.books.BookPageInfo;
import com.example.android.readaholic.contants_and_static_data.DummyBooks;
import com.example.android.readaholic.contants_and_static_data.SearchType;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //initializing search type spinner and adding items to it
        initializeSearchTypeSpinner();
        //setting click listeners
        setClickListeners();

        //checking for intent extras
        ///////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        //checking if an activity wants to search for books or not
        if(intent.getBooleanExtra("search",false) == true)
        {
            //if a search is required get the search key and search type and make a search request
                if(UserInfo.IsMemic) {
                    mimicSearch();
                } else {
                    searchRequest(intent.getStringExtra("searchKey")
                            ,intent.getStringExtra("searchType"));
                }


        }
        //////////////////////////////////////////////////////////////////////////////////////

    }

    /**
     * this method initializes the search type spinner and adding
     * Title , Genre , Author items to it
     */
    private void initializeSearchTypeSpinner() {
        Spinner searchTypeSpinner = (Spinner) findViewById(R.id.Search_searchType_spinner);
        ArrayAdapter<CharSequence> searchTypeAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, SearchType.types);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(searchTypeAdapter);

    }

    /**
     * this method sets the click listeners in the activity
     * search view -> on query text listener
     */
    private void setClickListeners() {

        //search view click listener
        /////////////////////////////////////////////////////////////////////////////////
        SearchView searchView = (SearchView) findViewById(R.id.Search_searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //must be overriden
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                if(UserInfo.IsMemic) {
                    mimicSearch();
                } else {
                    Spinner spinner = (Spinner)findViewById(R.id.Search_searchType_spinner) ;
                    String searchType = spinner.getSelectedItem().toString() ;
                    searchRequest(query,searchType);
                }


                return true;
            }

        });
        /////////////////////////////////////////////////////////////////////////

    }


    /**
     *sending a request to get the books searched by the user
     * @param searchKey the key user entered in the search view
     * @param searchType the type of the search user wants
     *                   it might be (Title,Genre,Author)
     */
    private void searchRequest(String searchKey,String searchType)  {
        //showing the progress bar to indicate that data is loading
        Loading();


        Urls urlController = new Urls(this ,this.getBaseContext());
        RequestQueue queue = Volley.newRequestQueue(this);



          String url =  Urls.ROOT + buildSearchParameters(searchKey,searchType) ;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    //getting the books searched by the user
                    ArrayList<bookSearchModel> books = parseSearchResponse(response);
                    if(books != null)
                    {
                        ListView list = (ListView)findViewById(R.id.Search_List);
                        bookSearchAdapter adapter = new bookSearchAdapter(getBaseContext(),books);
                        list.setAdapter(adapter);
                        showLayout();
                    } else {
                        //if no books were found
                        showMessage("No books found");
                    }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //checking what caused the error providing the user with appropriate message
                String message = null;

                if (error instanceof NetworkError || error instanceof NoConnectionError
                        || error instanceof TimeoutError) {
                    message = "Connection error...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                }

                //if error code is 405 I should show the error message to the user provided
                //by the backend
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 400) {
                    if(error.networkResponse.data!=null) {
                        //getting the error message provided by the backend
                       message = "No books found";
                    }
                }

                showMessage(message);


            }

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

              //  params.put("title","the bird king");

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private ArrayList<bookSearchModel> parseSearchResponse(String response) {
        ArrayList<bookSearchModel> books = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(response);
            JSONArray booksJson = root.getJSONArray("pages");
            if(booksJson.length() == 0 )
                return null;
            //getting books
          for(int i =0 ; i<booksJson.length() ; i++)
          {
              JSONObject bookJson = booksJson.getJSONObject(i);
              String title = bookJson.getString("title");
              String image = bookJson.getString("img_url");
              String author = bookJson.optString("author_name");
              String publicationDate = bookJson.getString("publication_date");
              String lastUpdate = bookJson.getString("updated_at");
              int pagesNum = bookJson.getInt("pages_no");
              int bookId = bookJson.getInt("id");
              int ratingCoung = bookJson.getInt("ratings_count");
              double ratingAvg = bookJson.getDouble("ratings_avg");

              books.add(new bookSearchModel(title,image,author,publicationDate
                      ,lastUpdate,pagesNum,bookId,ratingCoung,ratingAvg));

          }
            return books;

        }
        catch (JSONException E)
        {
            return null;
        }



    }
    private String parseErrorResponse(String response) {
        String errorMessage = "";

        try {

            JSONObject root = new JSONObject(response);
            errorMessage = root.getString("status");


        } catch (JSONException e) {
            errorMessage = "Please try again later";
        }

        return errorMessage;

    }

    private void Loading()
    {

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Search_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);

        ListView list = (ListView)findViewById(R.id.Search_List);
        list.setVisibility(View.GONE);

        TextView errorMessage = (TextView)findViewById(R.id.Search_errorMessage);
        errorMessage.setVisibility(View.GONE);
    }

    /**
     * showing the original layout
     */
    private void showLayout()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Search_ProgressBar);
        progressBar.setVisibility(View.GONE);

        ListView list = (ListView)findViewById(R.id.Search_List);
        list.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.Search_errorMessage);
        errorMessage.setVisibility(View.GONE);

    }

    /**
     * showing a message to the user
     * @param  -> message to be shown
     */
    private void showMessage(String message)
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Search_ProgressBar);
        progressBar.setVisibility(View.GONE);

        ListView list = (ListView)findViewById(R.id.Search_List);
        list.setVisibility(View.GONE);

        TextView errorMessage = (TextView)findViewById(R.id.Search_errorMessage);
        errorMessage.setVisibility(View.VISIBLE);
        errorMessage.setText(message);

    }


    /**
     * this function builds the a part of the url of the request of searching for books
     * @param searchKey
     * @param searchType it should be one of 3 values {Title , Genre , Author}
     * @return it returns the url of searching for books without the root
     */
    private String  buildSearchParameters(String searchKey , String searchType) {
        String parameter = "";

        if(searchType.equals("Author") )  //searching for books by author
            parameter += Urls.SEARCH_BY_AUTHOR + "?Author_name=" + searchKey;
        else if(searchType.equals("Genre") )  //searching for books by genre
            parameter += Urls.SEARCH_BY_GENRE + "?genreName=" + searchKey;
        else  //searching for books by title
            parameter += Urls.SEARCH_BY_TITLE + "?title=" + searchKey;

        //repacing every space with %20 for encoding
        return (String)parameter.replace(" ","%20");
    }

    private void mimicSearch()
    {
        ArrayList<bookSearchModel> books = parseSearchResponse(DummyBooks.book);
        bookSearchAdapter adapter = new bookSearchAdapter(this,books);
        ListView list = (ListView)findViewById(R.id.Search_List);
        list.setAdapter(adapter);
    }

}
