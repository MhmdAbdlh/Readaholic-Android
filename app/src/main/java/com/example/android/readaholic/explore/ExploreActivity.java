package com.example.android.readaholic.explore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.SearchType;
import com.example.android.readaholic.contants_and_static_data.Urls;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        initializeSearchTypeSpinner();
    }


    private void initializeSearchTypeSpinner() {
        Spinner searchTypeSpinner = (Spinner) findViewById(R.id.Explore_searchType_spinner);
        ArrayAdapter<CharSequence> searchTypeAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, SearchType.types);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(searchTypeAdapter);
    }

    private void setClickListeners() {

        /******************************search -> open*****************************/
        SearchView searchView = (SearchView) findViewById(R.id.Explore_searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //must be overriden
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

        });
        /******************************search -> close*****************************/

    }


    /**
     *sending a request to get the books searched by the user
     */
    private void searchRequest()
    {
      //  whileLoading();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.com/bins/734jo" ;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void parseSearchResponse() {

    }



    private String  buildSearchUrl() {
        String url = Urls.ROOT;

        return url;
    }



}
