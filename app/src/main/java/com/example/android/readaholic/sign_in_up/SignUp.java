package com.example.android.readaholic.sign_in_up;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Countries;
import com.example.android.readaholic.contants_and_static_data.Gender;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.settings.edit_Birthday.BirthdaySettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
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
    private void setClickListeners()
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
                date.setText(dayOfMonth + "/" + (month+1) + "/" + year);
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
                    signUpRequest();
                }

            }
        });

        /********************************signup click listener -> close************************/



    }

    /**
     *initializing the country spinner and gender spinner and providing them with data
     */
    private void initializeSpinners()
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
    private boolean validateFields()
    {
        //getting data that the user entered
        /***********************************getting data -> open**********************************/
        String email = ((EditText)findViewById(R.id.SignUp_email_edittext)).getText().toString();
        String userName = ((EditText)findViewById(R.id.SignUp_userName_edittext)).getText().toString();
        String firstName = ((EditText)findViewById(R.id.SignUp_firstName_edittext)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.SignUp_lastName_edittext)).getText().toString();
        String password = ((EditText)findViewById(R.id.SignUp_Pass_edittext)).getText().toString();
        String confirmPassword = ((EditText)findViewById(R.id.SignUp_rePass_edittext)).getText().toString();
        String birthday = ((Button)findViewById(R.id.SignUp_birthday_Button)).getText().toString();
        /**********************************getting data -> close*********************************/


        //checking if all fields are filled or not
        /**********************************checking filled -> open*********************************/
        if( email.length() == 0 || userName.length() == 0 || firstName.length() == 0 || birthday.length() == 0
                || lastName.length() == 0 || password.length() == 0 || confirmPassword.length() ==0  ) {

            showErrorMessage("Please fill all fields");
            return false;
        }
        /*********************************checking filled -> close***********************************/

        //checking if there are spaces in the email or username or the password
        /********************************checking spaces -> open***********************************/
        if(email.length() > email.replaceAll("\\s+","").length()
        || userName.length() > userName.replaceAll("\\s+","").length()
        || password.length() > password.replaceAll("\\s+","").length()) {

            showErrorMessage("Email , username and password should not contain spaces");
            return false;
        }
        /**********************************checking spaces -> close*******************************/

        /**********************************checking password -> open ******************************/
        if(!password.equals(confirmPassword)) {
            showErrorMessage("The two passwords you entered don't match");
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
    private void signUpRequest()
    {
        //showing the progress bar
        whileLoading();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.myjson.com/bins/1hdk";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //checking if the signup process succeeded or not
                        boolean parseResponse = parseSignUpResponse(response);
                        //if it succeeded user should verify the email
                        if(parseResponse == true){
                            finish();
                            //TODO you should make a new layout to inform the user to validate his email
                        } else {
                            //show the original layout
                            showLayout();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showErrorMessage("Error in Connection");
                showLayout();
            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    //endregion

    //region parse json responses
    /**
     * parsing the sign up request
     *
     * if the status is true -> it should return true indicating that the sign up was successfull
     *
     * if the status was false -> error message should be displayed informing the user what was
     * wrong in the data
     */
    private boolean parseSignUpResponse(String response)
    {
        try {
            JSONObject root = new JSONObject(response);
            //if the sign up process succeeded return true
            if (root.getString("status").equals("true") ) {
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

    //region ui control

    /**
     * showing the progress bar to indicate that the request is in process
     */
    private void whileLoading()
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
    private void showLayout()
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
     * @param errorMessage error message to be shown
     */
    private void showErrorMessage(String errorMessage)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(errorMessage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    //endregion




}
