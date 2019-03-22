package com.example.android.readaholic.settings.edit_Birthday;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Countries;
import com.example.android.readaholic.settings.Settings;
import com.example.android.readaholic.settings.edit_UserName.UserNameSettings;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class BirthdaySettings extends AppCompatActivity {
    //TODO change the possible responses when the back end gives more info
    /**
     *All possible Change birrthday responses
     * SUCCESS-> birthday changed successfully
     */
    enum ChangeBirthDayResponses {
        SUCCESS,
        FAILED,
        SERVER_ERROR
    }
    //TODO change the possible responses when the back end gives more info
    /**
     *All possible Change who can see my birthday responses
     * SUCCESS-> who can see my birth day changed successfully
     */
    enum ChangeWhoCanSeeMyBirthDayResponses {
        SUCCESS,
        FAILED,
        SERVER_ERROR
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_settings);

        //initializing click listeners and the layout to the default values
        setClickListeners();
        setDefaultBirthDay();
        setDefaultPrivacy();
    }



    //region initializations
    /**
     * sets the default birthdate
     */
    private void setDefaultBirthDay()
    {
        Button dateButton = (Button) findViewById(R.id.Birthday_Button);
        //setting the default birthdate
        Intent intent = getIntent();
        if(intent.hasExtra("birthDay")){
            //getting the date sent from the previews activity
            String date = intent.getStringExtra("birthDay");
            dateButton.setText(date);
        }
    }

    /**
     * sets the default of who can see my birthday setting
     */
    private void setDefaultPrivacy()
    {
        Intent intent = getIntent();
        if(intent.hasExtra("whoCanSeeMyBirthDay")) {
            String whoCanSeeMyBirthDay = intent.getStringExtra("whoCanSeeMyBirthDay");
            switch (whoCanSeeMyBirthDay) {
                case "EVERYONE":
                    RadioButton everyOne = (RadioButton) findViewById(R.id.Birthday_everyone_RadioButton);
                    everyOne.setChecked(true);

                    break;
                case "ONLYME":
                    RadioButton onlyMe = (RadioButton) findViewById(R.id.Birthday_onlyMe_RadioButton);
                    onlyMe.setChecked(true);
            }
        }
    }

    /**
     * dateButtonClickListener -> when the date button is clicked i should show
     *the date picker dialog with default of todays date
     *
     * mDateSetListener -> when a date is selected the text on the date button should be updated
     * with the selected date
     *
     * save birth day Text view -> when clicked should make a request to modify the birth day
     *
     * save who can see my birthday -> when clicked should make a request to modify who can see
     * my birthday
     */
    private void setClickListeners()
    {
        //getting the date button
        final Button date = (Button)findViewById(R.id.Birthday_Button);
        //setting the click listener

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting todays date to set it as a default date when user open the dialog
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //creating a new dialog and initializing it with the current date
                DatePickerDialog datePickerDialog = new DatePickerDialog(BirthdaySettings.this
                        ,android.R.style.Theme_Material_Light_Dialog
                        ,mDateSetListener
                        ,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });

        //setting the dialog click listener
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //updating the text when a date is selected
                date.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        };

        //Setting save birthday click listener
        final TextView saveBirthday = (TextView)findViewById(R.id.Birthday_saveBirthday_TextView);
        saveBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeBirthDayRequest();
            }
        });

        //Setting save who can see my birthday click listener
        final TextView saveWhoCanSeeMyBirthday = (TextView)findViewById(R.id.Birthday_saveWhoCanSeeMyBirthday_Textview);
        saveWhoCanSeeMyBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWhoCanSeeMyBirthDayRequest();
            }
        });



    }
    //endregion


    //region UI controlling
    /**
     * showing the progress bar to indicate that request is in progress
     */
    private void Loading()
    {

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Birthday_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.BirthDay_scrollView);
        scrollView.setVisibility(View.GONE);

        TextView errorMessage = (TextView)findViewById(R.id.Birthday_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);
    }

    /**
     * showing the original layout
     */
    private void showLayout()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Birthday_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.BirthDay_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.Birthday_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);

    }

    /**
     * showing an error message to the user
     * @param  -> error message to be shown
     */
    private void error(String error)
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Birthday_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.BirthDay_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.Birthday_errorMessage_TextView);
        errorMessage.setVisibility(View.VISIBLE);
        errorMessage.setText(error);

    }
    //endregion

    //region Requests
    /**
     * sending a request to save the birthday entered
     */
    private void changeBirthDayRequest()
    {
        Loading();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.myjson.com/bins/91qo2";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ChangeBirthDayResponses result = parseBirthDayResponse(response);
                        if(result == ChangeBirthDayResponses.SUCCESS) {

                            Toast.makeText(BirthdaySettings.this, "Birthday saved successfully"
                                    , Toast.LENGTH_SHORT).show();
                        }
                        else if(result == ChangeBirthDayResponses.FAILED) {
                            error("Saving failed");

                        } else if(result == ChangeBirthDayResponses.SERVER_ERROR) {
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
    /**
     * sending a request to save who can see my birth day
     */
    private void changeWhoCanSeeMyBirthDayRequest()
    {
        Loading();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.myjson.com/bins/91qo2";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ChangeWhoCanSeeMyBirthDayResponses result = parseWhoCanSeeMyBirthDayResponse(response);
                        if(result == ChangeWhoCanSeeMyBirthDayResponses.SUCCESS) {

                            Toast.makeText(BirthdaySettings.this
                                    ,"Who can see my birthday saved successfully"
                                    , Toast.LENGTH_SHORT).show();
                        }
                        else if(result == ChangeWhoCanSeeMyBirthDayResponses.FAILED) {
                            error("Saving failed");

                        } else if(result == ChangeWhoCanSeeMyBirthDayResponses.SERVER_ERROR) {
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

    //region parse responses
    /**
     * getting the response of changing the birth day
     * @param response
     * @return SUCCESS -> if the name changed successfully (status = true)
     *         FAILED -> if there was
     *
     */
    private ChangeBirthDayResponses parseBirthDayResponse(String response)
    {
        try {
            JSONObject root = new JSONObject(response);
            if (root.getString("status").equals("true") ) {

                return ChangeBirthDayResponses.SUCCESS;
            }
            else return ChangeBirthDayResponses.FAILED;

        }
        catch (JSONException E)
        {
            return ChangeBirthDayResponses.SERVER_ERROR;
        }
    }

    /**
     * getting the response of changing the user name
     * @param response
     * @return SUCCESS -> if the name changed successfully (status = true)
     *         FAILED -> if there was
     *
     */
    private ChangeWhoCanSeeMyBirthDayResponses parseWhoCanSeeMyBirthDayResponse(String response)
    {
        try {
            JSONObject root = new JSONObject(response);
            if (root.getString("status").equals("true") ) {

                return ChangeWhoCanSeeMyBirthDayResponses.SUCCESS;
            }
            else return ChangeWhoCanSeeMyBirthDayResponses.FAILED;

        }
        catch (JSONException E)
        {
            return ChangeWhoCanSeeMyBirthDayResponses.SERVER_ERROR;
        }
    }

    //endregion
}
