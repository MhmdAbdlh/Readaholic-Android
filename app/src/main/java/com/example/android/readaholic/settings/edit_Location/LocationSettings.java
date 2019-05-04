package com.example.android.readaholic.settings.edit_Location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.android.readaholic.contants_and_static_data.Countries;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.contants_and_static_data.WhoCanSeeContent;
import com.example.android.readaholic.settings.SettingMimicData;
import com.example.android.readaholic.settings.edit_Birthday.BirthdaySettings;
import com.pusher.client.channel.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class LocationSettings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_settings);

        //setting and initializing the spinner
        Spinner countriesSpinner = (Spinner)findViewById(R.id.Location_spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,Countries.countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);

        setClickListeners();
        setDefaultCountry();
        setDefaultPrivacy();




    }

    //region initializations

    /**
     * Setting the default country shown when opening the activity
     * it should be the country that the user signed up with
     * if not it will be Afganistan(first country in alphab)
     */
    private void setDefaultCountry()
    {
        Spinner countriesSpinner = (Spinner)findViewById(R.id.Location_spinner);
        //setting the default selected country of the spinner
        Intent intent = getIntent();
        if(intent.hasExtra("country")){
            //getting the country sent from the previews activity
            String country = intent.getStringExtra("country");
            int index = Countries.getCountryIndex(country);
            //if the country exists in the stored countries -> make it default in the spinner
            if(index >= 0){
                countriesSpinner.setSelection(index);
            } else {
                //if the country doesnt exist in the stored countries set the defalt to afganistan
                countriesSpinner.setSelection(0);
            }

        }
    }

    /**
     * sets the default of who can see my location setting
     */
    private void setDefaultPrivacy()
    {
        Intent intent = getIntent();
        if(intent.hasExtra("whoCanSeeMyLocation")) {
            String whoCanSeeMyLocation = intent.getStringExtra("whoCanSeeMyLocation");
            switch (whoCanSeeMyLocation) {
                case "EVERYONE":
                    RadioButton everyOne = (RadioButton) findViewById(R.id.Location_everynone_RadioButton);
                    everyOne.setChecked(true);

                    break;
                case "ONLYME":
                    RadioButton onlyMe = (RadioButton) findViewById(R.id.Location_onlyMe_RadioButton);
                    onlyMe.setChecked(true);
            }
        }
    }

    /**
     * save location Text view -> when clicked should make a request to modify the location
     *
     * save who can see my location -> when clicked should make a request to modify who can see
     * my location
     */
    private void setClickListeners()
    {
        //Setting save location click listener
        final TextView saveLocation = (TextView)findViewById(R.id.Location_saveCountry_TextView);
        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserInfo.IsMemic) {
                    //in case of mimic data
                    Spinner spinner = (Spinner)findViewById(R.id.Location_spinner);
                    SettingMimicData.sLocation = spinner.getSelectedItem().toString();
                    Toast.makeText(LocationSettings.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    //in case of connected to server
                    changeLocationRequest();
                }

            }
        });

        //Setting save who can see my location click listener
        final TextView saveWhoCanSeeMyLocation = (TextView)findViewById(R.id.Location_saveWhoCanSeeMyLocation_TextView);
        saveWhoCanSeeMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserInfo.IsMemic) {
                    WhoCanSeeContent whoCanSeeContent;
                    //getting the radio buttons for who can see my birth day
                    /////////////////////////////////////////////////////////////////////////////////////////
                    RadioButton everyone = ((RadioButton) findViewById(R.id.Location_everynone_RadioButton));
                    RadioButton onlyme = ((RadioButton) findViewById(R.id.Location_onlyMe_RadioButton));
                    /////////////////////////////////////////////////////////////////////////////////////////

                    //checking which radio button is clicked
                    ///////////////////////////////////
                    if(everyone.isChecked()) {
                        whoCanSeeContent = WhoCanSeeContent.EVERYONE;
                    } else {
                        whoCanSeeContent = WhoCanSeeContent.ONLYME;
                    }
                    SettingMimicData.sWhoCanSeeMyLocation = whoCanSeeContent;
                    Toast.makeText(LocationSettings.this, "Saved", Toast.LENGTH_SHORT).show();
                    ///////////////////////////////////
                } else {
                    changeWhoCanSeeMyCountryRequest();
                }

            }
        });


    }
    //endregion

    //region UI controls
    /**
     * showing the progress bar to indicate that request is in progress
     */
    private void Loading()
    {

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Location_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Location_scrollView);
        scrollView.setVisibility(View.GONE);

        TextView errorMessage = (TextView)findViewById(R.id.Location_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);
    }

    /**
     * showing the original layout
     */
    private void showLayout()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Location_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Location_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.Location_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);

    }

    /**
     * showing an error message to the user
     * @param  -> error message to be shown
     */
    private void error(String error)
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Location_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Location_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.Location_errorMessage_TextView);
        errorMessage.setVisibility(View.VISIBLE);
        errorMessage.setText(error);

    }
    //endregion

    //region requests
    /**
     * sending a request to save the location entered
     */
    private void changeLocationRequest()
    {
        Loading();
        Urls urlController = new Urls(this,this.getBaseContext());
        RequestQueue queue = Volley.newRequestQueue(this);

        String newCountry = ((Spinner)findViewById(R.id.Location_spinner)).getSelectedItem().toString();

        String url = Urls.ROOT + Urls.CHANGE_COUNTRY + "?" + urlController.constructTokenParameters()
                +"&newCountry=" + newCountry;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Toast.makeText(LocationSettings.this, "Country saved successfully"
                                    , Toast.LENGTH_SHORT).show();
                            showLayout();
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

                //if error code is 405 i should show the error message to the user provided
                //by the backend
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpURLConnection.HTTP_BAD_METHOD) {
                    if(error.networkResponse.data!=null) {
                        //getting the error message provided by the backend
                        try {
                            String response = new String(error.networkResponse.data, "UTF-8");
                            message = parseErrorResponse(response);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                }

                //showing error message to the user
                error(message);


            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    /**
     * sending a request to save who can see my Location
     */
    private void changeWhoCanSeeMyCountryRequest()
    {
        Loading();
        Urls urlController = new Urls(this,this.getBaseContext());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Urls.ROOT + Urls.WHO_CAN_SEE_MY_COUNTRY + "?" + urlController.constructTokenParameters()
                +"&" + urlController.getWhoCanSeeMyLocationParameters();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Toast.makeText(LocationSettings.this
                                    ,"Who can see my country saved successfully"
                                    , Toast.LENGTH_SHORT).show();

                            showLayout();
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


                //if error code is 405 i should show the error message to the user provided
                //by the backend
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpURLConnection.HTTP_BAD_METHOD) {
                    if(error.networkResponse.data!=null) {
                        //getting the error message provided by the backend
                        try {
                            String response = new String(error.networkResponse.data, "UTF-8");
                            message = parseErrorResponse(response);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                }


                //showing error message to the user
                error(message);

            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    //endregion

    //region parsing responses

    private String parseErrorResponse(String response) {
        String errorMessage = "";

        try {

            JSONObject root = new JSONObject(response);
            errorMessage = root.getString("errors");


        } catch (JSONException e) {
            errorMessage = "Please try again later";
        }

        return errorMessage;

    }


    //endregion

}
