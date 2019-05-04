package com.example.android.readaholic.sign_in_up;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setClickListeners();
    }
    /**
     *sending a request to get the user data
     *used when the status is true (valid username and password)
     */
    private void forgotPasswordRequest()
    {
        //showing the progress bar to indicate that data is loading
        whileLoading();

        Urls urlController = new Urls(this ,this.getBaseContext());
        RequestQueue queue = Volley.newRequestQueue(this);
        EditText email = (EditText)findViewById(R.id.ForgotPassword_email_edittext);

        String url = Urls.ROOT + Urls.FORGOT_PASSWORD + "?email=" + email.getText().toString() ;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String message = parseForgotPasswordResponse(response) ;
                        Toast.makeText(ForgotPassword.this, message, Toast.LENGTH_SHORT).show();
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

                //if error code is 405 i should show the error message to the user privided
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

                Toast.makeText(ForgotPassword.this, message, Toast.LENGTH_SHORT).show();
                showLayout();

            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }




    /**
     * it parses the json data provided in the user data string
     * @param response the json data string
     * @return it returns the the type of responses
     */
    private String parseForgotPasswordResponse(String response)
    {
        try {
            JSONObject root = new JSONObject(response);
            return root.optString("message");

        }
        catch (JSONException E)
        {
           return "please try again later";
        }


    }
    private String parseErrorResponse(String response) {
        String errorMessage = "";

        try {

            JSONObject root = new JSONObject(response);
            errorMessage = root.getString("error");


        } catch (JSONException e) {
            errorMessage = "Please try again later";
        }

        return errorMessage;

    }

    /**
     * shows the loading bar and makes the content of layout invisible
     * to indicate that the data is still loading
     */
    private void whileLoading()
    {
        //show the progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ForgotPassword_loading_progbar);
        progressBar.setVisibility(View.VISIBLE);

        //hide the original layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.ForgotPassword_scroll_scrollview);
        scrollView.setVisibility(View.INVISIBLE);

    }



    /**
     * shows a message to a user to inform him that there is an error
     * @param errorMessage the error message to be shown
     */
    private void showErrorMessage(String errorMessage)
    {
        //hiding the progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ForgotPassword_loading_progbar);
        progressBar.setVisibility(View.GONE);

        //showing the original view
        ScrollView scrollView = (ScrollView) findViewById(R.id.ForgotPassword_scroll_scrollview);
        scrollView.setVisibility(View.VISIBLE);

    }



    /**
     * shows the content of the layout and hides error text view
     */
    private void showLayout()
    {
        //hiding progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ForgotPassword_loading_progbar);
        progressBar.setVisibility(View.GONE);

        //showing the original layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.ForgotPassword_scroll_scrollview);
        scrollView.setVisibility(View.VISIBLE);

    }







    private void setClickListeners() {
        Button button = (Button)findViewById(R.id.ForgotPassword_getPassword_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordRequest();
            }
        });
    }
}
