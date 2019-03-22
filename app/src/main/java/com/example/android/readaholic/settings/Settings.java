package com.example.android.readaholic.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.WhoCanSeeContent;
import com.example.android.readaholic.settings.edit_Birthday.BirthdaySettings;
import com.example.android.readaholic.settings.edit_Location.LocationSettings;
import com.example.android.readaholic.settings.edit_UserName.UserNameSettings;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Settings extends AppCompatActivity {

    /**
     * provides all possible scenarios that could happen while requesting the settings data
     * ACCEPTED -> request and parse completed successfully
     * CONNECTION_ERROR -> user internet not connected
     * SERVER_ERROR -> error in server
     */
    public enum SettingsResponses{
        ACCEPTED,
        CONNECTION_ERROR,
        SERVER_ERROR
    }

    private SettingData mSettingData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsRequest();
        setClicklisteners();
    }

    //region initializations
    /**
     * sets the click listeners for different setting items(username,birthday,location)
     * when any of them is clicked a new activity should open to enable editting the corresponding
     * item
     */
    private void setClicklisteners()
    {
        //setting username on clock listener
        TextView userNameEdit = (TextView)findViewById(R.id.Settings_userNameEdit_TextView);
        userNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserNameSettings.class);
                intent.putExtra("userName",mSettingData.getmUserName());

                startActivity(intent);
            }
        });


        //setting birthday on click listener
        TextView birthDayEdit = (TextView)findViewById(R.id.Settings_birthDayEdit_TextView);
        birthDayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BirthdaySettings.class);
                //sending the birthday and who can see the birthday to the new activity
                intent.putExtra("birthDay",mSettingData.getmBirthDay());
                intent.putExtra("whoCanSeeMyBirthDay",mSettingData.getmWhoCanSeeMyBirthDay().toString());

                startActivity(intent);
            }
        });


        //setting location on click listener
        TextView location = (TextView)findViewById(R.id.Settings_locationEdit_TextView);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocationSettings.class);
                //sending location and who can see the location to the new activity
                intent.putExtra("location",mSettingData.getmLocation());
                intent.putExtra("whoCanSeeMyLocation",mSettingData.getmWhoCanSeeMyLocation().toString());

                startActivity(intent);
            }
        });

        //when user clicks on the reload button the request is sent again
        Button reload = (Button)findViewById(R.id.Settings_reload_Button);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsRequest();
            }
        });

    }
    //endregion

    //region requests
    /**
     *Requesting the settings data
     * if the response was a success -> fill the layout with the data
     * if the response was a failure -> provide suitable error message
     */
    private void settingsRequest()
    {
        Loading();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.myjson.com/bins/91qo2";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SettingsResponses parseResult =  parseSettingsItems(response);
                        if(parseResult == SettingsResponses.ACCEPTED) {
                            loadLayout();
                            noErrors();
                        }
                        else if(parseResult == SettingsResponses.SERVER_ERROR) {
                            error("Server error");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error("Connection error");
            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    //endregion

    //region responses
    /**
     * parsing all settings data and passing them to mSettingsData if succeeded
     * @param response the json response
     * @return returns the parsing status whether it succeeded or if there was an error
     * ACCEPTED-> the parsing succeeded
     * SERVER_ERROR -> there was an error while parsing the data
     */
    private SettingsResponses parseSettingsItems(String response){

        try {
                JSONObject root = new JSONObject(response);
                JSONObject userObject = root.getJSONObject("user");

                String userName = userObject.getString("userName");
                String birthDay = userObject.getString("birthday");
                String location = userObject.getString("location");
                String image = userObject.optString("image");
                String seeMyBirthDay = userObject.getString("seeMyBirthday");
                String seeMyLocation = userObject.getString("seeMyCountry");
                //converting who can see my birthday and who can see my location to the enum
                WhoCanSeeContent whoCanSeeMyBirthDay =
                     WhoCanSeeContent.valueOf(seeMyBirthDay.toUpperCase());
                WhoCanSeeContent whoCanSeeMyLocation =
                        WhoCanSeeContent.valueOf(seeMyLocation.toUpperCase());

                //setting the collected data to mSettingData to view the data in the layout
                mSettingData = new SettingData(userName,birthDay,image,location
                                              ,whoCanSeeMyBirthDay,whoCanSeeMyLocation);


                return SettingsResponses.ACCEPTED;

        }
        catch (JSONException E)
        {
            return SettingsResponses.SERVER_ERROR;
        }

    }
    //endregion

    //region UICOntrolling
    /**
     * loads the data mSettingsData to the layout
     */
    private void loadLayout()
    {
        //loading the username , birth day ,location
        TextView userName = (TextView)findViewById(R.id.Settings_userNameContent_TextView);
        userName.setText(mSettingData.getmUserName());

        TextView birthDay = (TextView)findViewById(R.id.Settings_birthDayContent_TextView);
        birthDay.setText(mSettingData.getmBirthDay());

        TextView location = (TextView)findViewById(R.id.Settings_locationContent_TextView);
        location.setText(mSettingData.getmLocation());

        //if the user doesn`t have an image -> load default image
        if(mSettingData.getmImage() == "") {
            ImageView profilePic = (ImageView)findViewById(R.id.Settings_Image_ImageView);
            profilePic.setImageResource(R.drawable.profileicon);
        } else {
            //load the image from the url using picasso
            ImageView profilePic = (ImageView)findViewById(R.id.Settings_Image_ImageView);
            Picasso.get()
                    .load(mSettingData.getmImage())
                    .error(R.drawable.profileicon)
                    .into(profilePic);
        }

    }



    /**
     * showing the progress bar to indicate that the progress is in progress
     */
    private void Loading()
    {

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Settings_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Settings_scroll_Layout);
        scrollView.setVisibility(View.GONE);

        RelativeLayout errorLayout = (RelativeLayout)findViewById(R.id.Settings_errors_Layout);
        errorLayout.setVisibility(View.GONE);
    }

    /**
     * showing the layout
     * should be called when the request was a success
     */
    private void noErrors()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Settings_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Settings_scroll_Layout);
        scrollView.setVisibility(View.VISIBLE);

        RelativeLayout errorLayout = (RelativeLayout)findViewById(R.id.Settings_errors_Layout);
        errorLayout.setVisibility(View.GONE);
    }

    /**
     * shown error message to the user
     * @param error
     */
    private void error(String error)
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Settings_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Settings_scroll_Layout);
        scrollView.setVisibility(View.GONE);

        RelativeLayout errorLayout = (RelativeLayout)findViewById(R.id.Settings_errors_Layout);
        errorLayout.setVisibility(View.VISIBLE);

        TextView errorText = (TextView)findViewById(R.id.Settings_errorMessage_TextView);
        errorText.setText(error);
    }
    //endregion


}
