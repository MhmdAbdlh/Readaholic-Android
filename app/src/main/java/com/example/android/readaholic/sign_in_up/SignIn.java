package com.example.android.readaholic.sign_in_up;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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

import com.example.android.readaholic.contants_and_static_data.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sign in activity
 *
 * handles the sign in process
 * it goes to the main activity if the user exits
 * else it provides the user with the error message
 */
public class SignIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setClickListeners();
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
      // String url = "http://"+"localhost"+":8000/api/logIn"+constructParameters();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        boolean parseResponse = parseUserData(response);
                        if(parseResponse == true){
                            Intent intent = new Intent(getBaseContext(),Main.class);
                            startActivity(intent);
                            finish();
                        }


                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showErrorMessage("Error in Connection");
                    }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    //endregion

    //region parsing request
    /**
     * it parses the json data provided in the user data string
     * @param userData the json data string
     * @return it returns the the type of responses
     */
    private boolean parseUserData(String userData)
    {
        try {
            JSONObject root = new JSONObject(userData);
            if (root.getString("status").equals("true") ) {
                //getting user info
                /****************************getting user info -> open*****************************/
                String token = root.getString("token");
                JSONObject userObject = root.getJSONObject("user");
                String userName = userObject.getString("userName");
                String name = userObject.getString("name");
                String image = userObject.optString("image");
                /****************************getting user info -> close****************************/
                //adding data to the static class to be used later
                UserInfo.addUserInfo(userName,name,image,token);

                return true;
            }
            else {
                //if the sign up process failed show error message
                String error = "";

                //getting error messages
                JSONArray errorMessage = root.getJSONArray("errors");
                for (int i =0;i<errorMessage.length() ; i++)
                {
                    error += errorMessage.getString(i) + '\n';
                }

                //display them to the user
                showErrorMessage(error);
                return false;
            }
        }
        catch (JSONException E)
        {
            showErrorMessage("Server error");
            return false;
        }


    }
    //endregion

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
        //show the progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignIn_loading_progbar);
        progressBar.setVisibility(View.VISIBLE);

        //hide the original layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.SignIn_scroll_scrollview);
        scrollView.setVisibility(View.INVISIBLE);

        //hiding the error message
        TextView error = (TextView)findViewById(R.id.SignIn_error_textview);
        error.setVisibility(View.INVISIBLE);
    }



    /**
     * shows a message to a user to inform him that there is an error
     * @param errorMessage the error message to be shown
     */
    private void showErrorMessage(String errorMessage)
    {
        //hiding the progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignIn_loading_progbar);
        progressBar.setVisibility(View.GONE);

        //showing the original view
        ScrollView scrollView = (ScrollView) findViewById(R.id.SignIn_scroll_scrollview);
        scrollView.setVisibility(View.VISIBLE);

        //showing error message
        TextView error = (TextView)findViewById(R.id.SignIn_error_textview);
        error.setText(errorMessage);
        error.setVisibility(View.VISIBLE);

    }



    /**
     * shows the content of the layout and hides error text view
     */
    private void showLayout()
    {
        //hiding progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignIn_loading_progbar);
        progressBar.setVisibility(View.GONE);

        //showing the original layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.SignIn_scroll_scrollview);
        scrollView.setVisibility(View.VISIBLE);

        //hiding the error message
        TextView error = (TextView)findViewById(R.id.SignIn_error_textview);
        error.setVisibility(View.INVISIBLE);
    }

    //endregion

    //region initializations


    /**
     * sets all click listeners in the signin layout
     *
     * signin button click listener -> checking if the the user name and password belong to a user
     * or if they contain any error
     *
     */
    private void setClickListeners()
    {
        //setting Signin button clicklistener
        /******************************Signin click listener -> open*****************************************/
        Button signIn = (Button) findViewById(R.id.SignIn_signin_btn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //in case of mocking data
                /**********************mocking data -> open***************************************/


                //getting user name and password

                Intent intent1 = new Intent(getBaseContext(),Main.class);
                startActivity(intent1);

                /*EditText username = (EditText) findViewById(R.id.SignIn_userName_edittext);
                EditText pass = (EditText)findViewById(R.id.SignIn_password_edittext);

                //checking user name and password fields
                if(validateFields()) {
                    if (username.getText().toString().equals("admin")
                            && pass.getText().toString().equals("admin")) {
                        //filling the static class with dummy data
                        fillDummyData();
                        //starting main activity
                        Intent intent = new Intent(v.getContext(), Main.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //if the user name and password dont match admin , admin show error message
                        showErrorMessage("Please check your email and password");
                    }
                }*/


                /**************************mocking data -> close*****************************************/


                //in case of connected to the server
                /**************************server connected -> open***************************************/
               /*
                if(validateFields()) {
                    //hides the keyboard when user clicks on sign in
                    hideSoftKeyboard(SignIn.this, v);
                    //checking if the user data is correct or not
                    getUserData();
                }
                */
               /***************************server connected -> close***************************************/


            }
        });
        /******************************Signin click listener -> close*********************************************/
    }
    private void fillDummyData()
    {
        UserInfo.addUserInfo("Ahmed","Waled"
                ,"https://unsplash.com/photos/HUBofEFQ6CA","1234567");
    }




    /**
     * constructs the structure of the parameters that should be added to the url
     * @return the parameters that should be concatenated with the root url
     */
    private String constructParameters()
    {
        //getting parameters
        String userName = ((EditText)findViewById(R.id.SignIn_userName_edittext)).getText().toString();
        String pass = ((EditText)findViewById(R.id.SignIn_password_edittext)).getText().toString();
        // return "?email=Ahmed@yahoo.com&password=Waled21";
        //concatenating parameters and sending them
        return "?email=" + userName + "&password=" + pass ;
    }





    /**
     * testing if the user name and password are filled or not
     * and if they contain spaces
     *
     * @return it returns true if they pass all tests
     *          it return false if they fail in any test
     */
    private boolean validateFields()
    {

        //getting the data user entered
        /*******************************getting data -> open**************************************/
        String userName = ((EditText)findViewById(R.id.SignIn_userName_edittext)).getText().toString();
        String pass = ((EditText)findViewById(R.id.SignIn_password_edittext)).getText().toString();
        /********************************getting data -> close**********************************/



        //validating username and password fields

        //checking if the username field is empty or not
        /********************************Checking empty fields -> open*********************************/
        if(userName.length() == 0 ||  pass.length() == 0){
         showErrorMessage("Please fill the username and password fields");

         return false;
        }
         /********************************Checking empty fields -> close******************************/



         //checking if there were spaces in the username or the password
        /********************************Checking white spaces -> open*******************************/
        if(userName.length() > userName.replaceAll("\\s+","").length()
                || pass.length() > pass.replaceAll("\\s+","").length()) {

            showErrorMessage("UserName or password should not contain spaces");
            return false;
        }
         /*******************************Checking white spaces -> close******************************/
        return true;

    }
    //endregion




}
