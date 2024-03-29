package com.example.android.readaholic.sign_in_up;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Countries;
import com.example.android.readaholic.contants_and_static_data.Gender;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.settings.edit_Birthday.BirthdaySettings;
import com.pusher.client.channel.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    public DatePickerDialog.OnDateSetListener mDateSetListener;
    //for unit testing
    public AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        initializeSpinners();
        setClickListeners();

 
    }

    //region initializations
    /**
     * setting all click listeners in the signup page
     *
     * date dialog click listener -> when date is selected it should be displayed
     *
     * sign up click listener -> when signup button is clicked it should validate
     * if the fields are valid or not and then make the request
     *
     */
    public void setClickListeners()
    {
        //getting the date button
        final Button date = (Button)findViewById(R.id.SignUp_birthday_Button);
        //setting the click listener
        /**************************date click listener -> open *********************************************/
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting today's date to set it as a default date when user open the dialog

                  //getting today's date
                 /****************getting date -> Open ******************/
                  Calendar cal = Calendar.getInstance();
                  int year = cal.get(Calendar.YEAR);
                  int month = cal.get(Calendar.MONTH);
                  int day = cal.get(Calendar.DAY_OF_MONTH);
                 /*****************getting date -> Close********************/


                //creating a new dialog and initializing it with the current date
                /********************************new dialog -> open**********************************/
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this
                        ,android.R.style.Theme_Material_Light_Dialog
                        ,mDateSetListener
                        ,year,month,day);
                //setting background color of the dialog
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
                /**********************************new dialog -> close*******************************/
            }
        });
        /*******************************************date click listener -> close*******************/

        //setting the dialog click listener
        /********************************dialog click listener -> open************************/
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //updating the text when a date is selected
                date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        };
        /*********************************dialog click listener -> close**************************/


        //signUp button click listener
        /********************************signup click listener -> open*************************/
        Button signUp = (Button)findViewById(R.id.SignUp_signUp_btn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()){
                    if(UserInfo.IsMemic) {
                        Toast.makeText(SignUp.this, "Congratulations", Toast.LENGTH_SHORT).show();
                    } else {
                        signUpRequest();
                    }

                }

            }
        });

        /********************************signup click listener -> close************************/



    }

    /**
     *initializing the country spinner and gender spinner and providing them with data
     */
    public void initializeSpinners()
    {
        //setting and initializing country spinner
        /*****************************country spinner -> open************************************/
        Spinner countriesSpinner = (Spinner)findViewById(R.id.SignUp_location_Spinner);
        ArrayAdapter<CharSequence> locationAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Countries.countries);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(locationAdapter);
        /****************************country spinner -> close************************************/


        //setting and initializing gender spinner
        /*******************************gender spinner ->open***********************************/
        Spinner genderSpinner = (Spinner)findViewById(R.id.SignUp_gender_Spinner);
        ArrayAdapter<CharSequence> genderAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Gender.genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        /********************************gender spinner -> close**********************************/

    }

    /**
     * validating all the fields and checking if they are filled and dont contain spaces
     * @return true if they passed all the tests
     *         false if there is a problem in any field
     */
    public boolean validateFields()
    {
        //getting data that the user entered
        /***********************************getting data -> open**********************************/
        String email = ((EditText)findViewById(R.id.SignUp_email_edittext)).getText().toString();
        String firstName = ((EditText)findViewById(R.id.SignUp_firstName_edittext)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.SignUp_lastName_edittext)).getText().toString();
        String password = ((EditText)findViewById(R.id.SignUp_Pass_edittext)).getText().toString();
        String confirmPassword = ((EditText)findViewById(R.id.SignUp_rePass_edittext)).getText().toString();
        String birthday = ((Button)findViewById(R.id.SignUp_birthday_Button)).getText().toString();
        String city = ((EditText)findViewById(R.id.SignUp_city_edittext)).getText().toString();
        /**********************************getting data -> close*********************************/


        //checking if all fields are filled or not
        /**********************************checking filled -> open*********************************/
        if( email.length() == 0 || firstName.length() == 0 || birthday.length() == 0 || city.length() ==0
                || lastName.length() == 0 || password.length() == 0 || confirmPassword.length() ==0  ) {

            showMessage("Please fill all fields" , "Error");
            return false;
        }
        /*********************************checking filled -> close***********************************/

        //checking if there are spaces in the email  or the password
        /********************************checking spaces -> open***********************************/
        if(email.length() > email.replaceAll("\\s+","").length()
        || password.length() > password.replaceAll("\\s+","").length()) {

            showMessage("Email , username and password should not contain spaces" , "Error");
            return false;
        }
        /**********************************checking spaces -> close*******************************/

        /**********************************checking password -> open ******************************/
        if(!password.equals(confirmPassword)) {
            showMessage("The two passwords you entered don't match" , "Error");
            return false;
        }
        /*********************************checking password ->close ****************************/

        //return true if it passes all the tests
        return true;

    }

    //endregion


    //region request

    /**
     * sending the signup request
     *
     * if the request was a success the user should be informed to check his email and confirm
     * the signup process
     *
     * if there was a problem the user would be informed by an error message
     */
    public void signUpRequest()
    {
        //showing the progress bar
        whileLoading();
        Urls urlController = new Urls(this,this.getBaseContext());

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Urls.ROOT + Urls.SIGN_UP ;//+ "?" + getSignUpParameters() ;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     showLayout();
                     showMessage("You signed up successfully ! please login to continue"
                             , "Congratulations");
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

                showMessage(message,"Error");
                showLayout();

            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                //getting user data
                /**********************************************************************************/
                String email = ((EditText)findViewById(R.id.SignUp_email_edittext)).getText().toString();
                String password = ((EditText)findViewById(R.id.SignUp_Pass_edittext)).getText().toString();
                String confPassword = ((EditText)findViewById(R.id.SignUp_rePass_edittext)).getText().toString();
                String birthday = ((Button)findViewById(R.id.SignUp_birthday_Button)).getText().toString();
                String country = ((Spinner)findViewById(R.id.SignUp_location_Spinner)).getSelectedItem().toString();
                String city = ((EditText)findViewById(R.id.SignUp_city_edittext)).getText().toString();
                String gender = ((Spinner)findViewById(R.id.SignUp_gender_Spinner)).getSelectedItem().toString();
                String firstName = ((EditText)findViewById(R.id.SignUp_firstName_edittext)).getText().toString();
                String lastName = ((EditText)findViewById(R.id.SignUp_lastName_edittext)).getText().toString();

                //getting full name from first and last name
                String name = firstName + " " + lastName;
                /**********************************************************************************/

                //constructing parameters
                params.put("email", email);
                params.put("name", name);
                params.put("password",password);
                params.put("password_confirmation", confPassword);
                params.put("country",country);
                params.put("city",city);
                params.put("birthday",birthday);
                params.put("gender",gender);


                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    //endregion

    //region parse json responses


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

    //region ui control

    /**
     * showing the progress bar to indicate that the request is in process
     */
    public void whileLoading()
    {
        //make progress bar visible
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignUp_proressBar);
        progressBar.setVisibility(View.VISIBLE);

        //make the oriinal layout invisible
        ScrollView scrollView = (ScrollView) findViewById(R.id.SignUp_Scrolliew);
        scrollView.setVisibility(View.INVISIBLE);
    }

    /**
     * shown the layout after the request is done
     */
    public void showLayout()
    {
        //hide progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.SignUp_proressBar);
        progressBar.setVisibility(View.GONE);

        //show original layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.SignUp_Scrolliew);
        scrollView.setVisibility(View.VISIBLE);
    }

    /**
     * showing error message to the user
     * @param message message to be shown
     * @param title title of the message
     */
    public void showMessage(String message , String title)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        this.alertDialog = alertDialog;
    }
    //endregion

    public AlertDialog getAlertDialog()
    {
        return this.alertDialog;
    }
    private void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


}
