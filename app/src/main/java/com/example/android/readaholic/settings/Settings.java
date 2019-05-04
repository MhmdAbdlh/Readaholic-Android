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
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.contants_and_static_data.WhoCanSeeContent;
import com.example.android.readaholic.settings.edit_Birthday.BirthdaySettings;
import com.example.android.readaholic.settings.edit_Location.LocationSettings;
import com.example.android.readaholic.settings.edit_Password.password;
import com.example.android.readaholic.settings.edit_ProfilePicture.ProfilePicture;
import com.example.android.readaholic.settings.edit_UserName.UserNameSettings;
import com.pusher.client.channel.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class Settings extends AppCompatActivity {
    private SettingData mSettingData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView changeImage = (TextView)findViewById(R.id.Settings_editPicture_textView);
        if(UserInfo.IsMemic) {
            //user cant change image in case of mimic
        changeImage.setVisibility(View.INVISIBLE);
        } else {
            changeImage.setVisibility(View.VISIBLE);
            settingsRequest();
        }

        setClicklisteners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UserInfo.IsMemic) {
            mimicSettings();
        } else {
            settingsRequest();
        }

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
        ///////////////////////////////////////////////////////////////////////
        TextView userNameEdit = (TextView)findViewById(R.id.Settings_userNameEdit_TextView);
        userNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserNameSettings.class);
                if(UserInfo.IsMemic) {
                    //in case of mimic data
                    intent.putExtra("userName",SettingMimicData.sName);
                } else {
                    //in case of connected to server
                    intent.putExtra("userName",mSettingData.getmName());
                }

                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////

        //setting birthday on click listener
        /////////////////////////////////////////////////////////////////////////
        TextView birthDayEdit = (TextView)findViewById(R.id.Settings_birthDayEdit_TextView);
        birthDayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BirthdaySettings.class);
                //sending the birthday and who can see the birthday to the new activity
                if(UserInfo.IsMemic) {
                    //in case of mimic data
                    intent.putExtra("birthDay",SettingMimicData.sBirthday);
                    intent.putExtra("whoCanSeeMyBirthDay",SettingMimicData.sWhoCanSeeMyBirthDay.toString());

                } else {
                    //in case of connected to server
                    intent.putExtra("birthDay",mSettingData.getmBirthDay());
                    intent.putExtra("whoCanSeeMyBirthDay",mSettingData.getmWhoCanSeeMyBirthDay().toString());

                }

                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////////////////

        //setting location on click listener
        /////////////////////////////////////////////////////////////////////////
        TextView location = (TextView)findViewById(R.id.Settings_locationEdit_TextView);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocationSettings.class);
                //sending location and who can see the location to the new activity
                if(UserInfo.IsMemic) {
                    //in case of mimic data
                    intent.putExtra("country",SettingMimicData.sLocation);
                    intent.putExtra("whoCanSeeMyLocation",SettingMimicData.sWhoCanSeeMyLocation.toString());
                } else {
                    //in case of connected to server
                    intent.putExtra("country",mSettingData.getmCountry());
                    intent.putExtra("whoCanSeeMyLocation",mSettingData.getmWhoCanSeeMyLocation().toString());

                }

                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////////

        //setting edit picture click listener
        //////////////////////////////////////////////////////////
        TextView editPicture = (TextView)findViewById(R.id.Settings_editPicture_textView);
        editPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfilePicture.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////

        //setting edit password click listener
        //////////////////////////////////////////////////////////
        TextView editPassword = (TextView)findViewById(R.id.Settings_editPassword_textView);
        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), password.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////


        //when user clicks on the reload button the request is sent again
        //////////////////////////////////////////////////////////
        Button reload = (Button)findViewById(R.id.Settings_reload_Button);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsRequest();
            }
        });
        //////////////////////////////////////////////////////////

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

        Urls urlController = new Urls(this,this);

        RequestQueue queue = Volley.newRequestQueue(this);
       // String url ="https://api.myjson.com/bins/91qo2";


           String url = Urls.ROOT + Urls.SHOW_SETTINGS + "?" + urlController.constructTokenParameters();


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        boolean parseResult =  parseSettingsItems(response);
                        if(parseResult == true) {
                            loadLayout();
                            noErrors();
                        }
                        else if(parseResult == false) {
                            error("Parsing error! Please try again after some time!!");
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

                //showing error message to the user
                error(message);
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
     * @return returns if the parsing completed successfully or not
     *
     */
    private boolean parseSettingsItems(String response){

        try {
                JSONObject root = new JSONObject(response);
                JSONObject userObject = root.getJSONObject("user");

                String name = userObject.getString("name");
                String birthDay = userObject.getString("birthday");
                String country = userObject.getString("country");
                String image = userObject.optString("image_link");
                String seeMyBirthDay = userObject.getString("see_my_birthday");
                String seeMyLocation = userObject.getString("see_my_country");
                //converting who can see my birthday and who can see my location to the enum
                WhoCanSeeContent whoCanSeeMyBirthDay =
                     WhoCanSeeContent.valueOf(seeMyBirthDay.toUpperCase());
                WhoCanSeeContent whoCanSeeMyLocation =
                        WhoCanSeeContent.valueOf(seeMyLocation.toUpperCase());

                //setting the collected data to mSettingData to view the data in the layout
                mSettingData = new SettingData(name,birthDay,image,country
                                              ,whoCanSeeMyBirthDay,whoCanSeeMyLocation);


                return true;

        }
        catch (JSONException E)
        {
            return false;
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
        userName.setText(mSettingData.getmName());

        TextView birthDay = (TextView)findViewById(R.id.Settings_birthDayContent_TextView);
        birthDay.setText(mSettingData.getmBirthDay());

        TextView location = (TextView)findViewById(R.id.Settings_locationContent_TextView);
        location.setText(mSettingData.getmCountry());

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

    private void mimicSettings()
    {
        TextView email = (TextView)findViewById(R.id.Settings_userNameContent_TextView);
        TextView birthDay = (TextView)findViewById(R.id.Settings_birthDayContent_TextView);
        TextView location = (TextView)findViewById(R.id.Settings_locationContent_TextView);
        ImageView profilePic = (ImageView)findViewById(R.id.Settings_Image_ImageView);

        email.setText(SettingMimicData.sName);
        birthDay.setText(SettingMimicData.sBirthday);
        location.setText(SettingMimicData.sLocation);
        profilePic.setImageResource(R.drawable.profileicon);


    }

}
