package com.example.android.readaholic.sign_in_up;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {

    /**
     * this enum covers all the type of responses i could get
     * ACCEPTED_USER  if the email and password are right
     * WRONG_USER if the email or password are incorrect
     * SERVER_ERROR if the url is wrong
     * CONNECTION_ERROR when the internet of the user is off
     */
    public enum SignInResponses{
        WRONG_USER,
        ACCEPTED_USER,
        CONNECTION_ERROR,
        SERVER_ERROR
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setBackgroundDrawableResource(R.drawable.open_book);

        //setting Signin button clicklistener
        Button signIn = (Button) findViewById(R.id.SignIn_signin_btn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),Main.class);
                startActivity(intent);
                finish();
                /*
                //hides the keyboard when user clicks on sign in
                hideSoftKeyboard(SignIn.this, v);
                //checking if the user data is correct or not
                getUserData();
                */

            }
        });


    }

    //region request
    /**
     *sending a request to get the user data
     *used when the status is true (valid username and password)
     */
    private void getUserData()
    {
        whileLoading();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.myjson.com/bins/1hdk";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        SignInResponses parseResponse = parseUserData(response);
                        if(parseResponse == SignInResponses.ACCEPTED_USER){
                            noErrors();
                            Intent intent = new Intent(getBaseContext(),Main.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (parseResponse == SignInResponses.WRONG_USER){
                        error("Please check your email and password");
                        }
                        else {
                            error("Server error");
                        }



                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error("Error in Connection");
                    }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    //endregion
    /**
     * it parses the json data provided in the user data string
     * @param userData the json data string
     * @return it returns the the type of responses
     */
    private SignInResponses parseUserData(String userData)
    {
        try {
            JSONObject root = new JSONObject(userData);
            if (root.getString("status").equals("true") ) {
                String token = root.getString("token");

                JSONObject userObject = root.getJSONObject("user");
                String userName = userObject.getString("userName");
                String name = userObject.getString("name");
                String image = userObject.optString("image");

                UserInfo.addUserInfo(userName,name,image,token);

                return SignInResponses.ACCEPTED_USER;
            }
            else return SignInResponses.WRONG_USER;

        }
        catch (JSONException E)
        {
            return SignInResponses.SERVER_ERROR;
        }


    }


    //region ui
    /**
     * this method hides the keyboard
     * used when the user clicks sign in to hide the keyboard to enable the user to see
     * the error messages if there is
     * @param activity
     * @param view
     */
    private  void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    /**
     * shows the loading bar and makes the content of layout invisible
     * to indicate that the data is still loading
     */
    private void whileLoading()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignIn_loading_progbar);
        progressBar.setVisibility(View.VISIBLE);

        ScrollView scrollView = (ScrollView) findViewById(R.id.SignIn_scroll_scrollview);
        scrollView.setVisibility(View.INVISIBLE);

        TextView error = (TextView)findViewById(R.id.SignIn_error_textview);
        error.setVisibility(View.INVISIBLE);
    }

    /**
     * shows a message to a user to inform him that there is an error
     */
    private void error(String errorMessage)
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignIn_loading_progbar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView) findViewById(R.id.SignIn_scroll_scrollview);
        scrollView.setVisibility(View.VISIBLE);

        TextView error = (TextView)findViewById(R.id.SignIn_error_textview);
        error.setText(errorMessage);
        error.setVisibility(View.VISIBLE);

    }
    /**
     * shows the content of the layout and hides all errors text views
     */
    private void noErrors()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignIn_loading_progbar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView) findViewById(R.id.SignIn_scroll_scrollview);
        scrollView.setVisibility(View.VISIBLE);

        TextView error = (TextView)findViewById(R.id.SignIn_error_textview);
        error.setVisibility(View.INVISIBLE);
    }

    //endregion

    /**
     * it checks if the user is connected to the internet
     * @return true if the user is connected, false if not connected
     */
   private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


}
