package com.example.android.readaholic.settings.edit_UserName;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.example.android.readaholic.contants_and_static_data.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class UserNameSettings extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_settings);

        setClickListeners();
        setDefaultUserName();
    }
    //region initialization

    /**
     * setting click listeners
     * save text view -> when clicked should make the request
     */
    private void setClickListeners(){
        TextView saveUserName = (TextView)findViewById(R.id.Username_saveUserName_TextView);
        saveUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userName = (TextView)findViewById(R.id.UserName_EditTex);
                if(userName.getText().toString().trim() == "") {
                    error("please enter avalid userName");
                } else {
                    saveUserNameRequest();
                }

            }
        });

    }

    /**
     *setting the default user name in the edit text
     * it should be the user name sent from the previous activity
     */
    private void setDefaultUserName()
    {
        EditText editText = (EditText)findViewById(R.id.UserName_EditTex);
        Intent intent = getIntent();
        if(intent.hasExtra("userName")){
            //getting the user name sent from previous activity
            String userName = intent.getStringExtra("userName");
            //setting the name to the edit text
            editText.setText(userName);
        } else {
            editText.setText("");
            editText.setHint("enter the user name");
        }
    }
    //endregion

    //region UI controlling

    /**
     * showing the progress bar to indicate that request is in progress
     */
    private void Loading()
    {

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.UserName_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.UserName_scrollView);
        scrollView.setVisibility(View.GONE);

        TextView errorMessage = (TextView)findViewById(R.id.UserName_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);
    }

    /**
     * showing the original layout
     */
    private void showLayout()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.UserName_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.UserName_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.UserName_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);

    }

    /**
     * showing an error message to the user
     * @param error
     */
    private void error(String error)
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.UserName_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.UserName_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.UserName_errorMessage_TextView);
        errorMessage.setVisibility(View.VISIBLE);
        errorMessage.setText(error);

    }

    //endregion

    //region request
    /**
     * sending a request to save the username entered
     */
    private void saveUserNameRequest()
    {
        Loading();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.myjson.com/bins/91qo2";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        boolean result = parseUserNameResponse(response);
                        if(result == true) {

                            Toast.makeText(UserNameSettings.this, "user name saved successfully"
                                    , Toast.LENGTH_SHORT).show();
                        }
                       else {
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
     * getting the response of changing the user name
     * @param response
     * @return true -> if the name changed successfully (status = true)
     *         false -> if there was
     *
     */
    private boolean parseUserNameResponse(String response) {
        try {
            JSONObject root = new JSONObject(response);
            if (root.getString("status").equals("true")) {

                return true;
            }
            else {
                return  false;
            }


        } catch (JSONException E) {
            return false;
        }
    }
    //endregion

}
