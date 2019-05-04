package com.example.android.readaholic.settings.edit_Password;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.settings.edit_UserName.UserNameSettings;
import com.example.android.readaholic.sign_in_up.SignUp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class password extends AppCompatActivity {

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        setClickListeners();

    }
    //region initialization

    /**
     * setting click listeners
     * save text view -> when clicked should make the request
     */
    private void setClickListeners(){
        TextView saveNewPassword = (TextView)findViewById(R.id.Password_savePassword_button);
        saveNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateNewPassword()) {
                    if(UserInfo.IsMemic) {
                        String currentPass = ((TextView)(findViewById(R.id.Password_CurrentPass_EditTex))).getText().toString();
                        if((currentPass.equals("admin"))){
                            Toast.makeText(password.this, "New Password saved", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        saveNewPasswordRequest();
                    }
                }



            }
        });

    }

    private boolean validateNewPassword() {
        //getting the data user entered
        /*******************************getting data -> open**************************************/
        String currentPass = ((EditText)findViewById(R.id.Password_CurrentPass_EditTex)).getText().toString();
        String newPass = ((EditText)findViewById(R.id.Password_newPassword_editText)).getText().toString();
        String confirmPass = ((EditText)findViewById(R.id.Password_confirmPassword_editText)).getText().toString();
        /********************************getting data -> close**********************************/



        //validating password fields

        //checking if any password field is empty or not
       /*****************************************************************************************/
        if(currentPass.length() == 0 ||  newPass.length() == 0 || confirmPass.length() == 0 ){
            showMessage("Please fill all fields" , "Error");
            return false;
        }
        /*****************************************************************************************/


        //checking if there were spaces in any password field
        /*****************************************************************************************/
        if(currentPass.length() > currentPass.replaceAll("\\s+","").length()
                || newPass.length() > newPass.replaceAll("\\s+","").length()
                || confirmPass.length() > confirmPass.replaceAll("\\s+","").length()){

            showMessage("password should not contain spaces" , "Error");
            return false;
        }
        /****************************************************************************************/
        if(!newPass.equals(confirmPass))
        {
            showMessage("Your password and confirmation password do not match" , "Error");
            return false;
        }

        //checking mismatching passwords
        /***************************************************************************************/
        return true;
    }


    //endregion

    //region UI controlling

    /**
     * showing the progress bar to indicate that request is in progress
     */
    private void Loading()
    {

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Password_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Password_scrollView);
        scrollView.setVisibility(View.GONE);

        TextView errorMessage = (TextView)findViewById(R.id.Password_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);
    }

    /**
     * showing the original layout
     */
    private void showLayout()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Password_progressBar);
        progressBar.setVisibility(View.GONE);

        ScrollView scrollView = (ScrollView)findViewById(R.id.Password_scrollView);
        scrollView.setVisibility(View.VISIBLE);

        TextView errorMessage = (TextView)findViewById(R.id.Password_errorMessage_TextView);
        errorMessage.setVisibility(View.GONE);

    }



    //endregion

    //region request
    /**
     * sending a request to save the username entered
     */

    private void saveNewPasswordRequest()
    {
        Loading();

        Urls urlController = new Urls(this,this);

        //getting password added by user
        /*******************************************************************************************/
        String oldPass = ((EditText)findViewById(R.id.Password_CurrentPass_EditTex)).getText().toString();
        String newPass = ((EditText)findViewById(R.id.Password_newPassword_editText)).getText().toString();
        String confirmPass = ((EditText)findViewById(R.id.Password_CurrentPass_EditTex)).getText().toString();

        /*******************************************************************************************/
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Urls.ROOT + Urls.CHANGE_PASSWORD +
                "?" + urlController.constructTokenParameters() + '&' +buildPasswordParameters();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(password.this, "New password saved successfully"
                                , Toast.LENGTH_SHORT).show();
                        finish();
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
                showMessage(message , "Error");
                showLayout();


            }

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    //endregion

    //region response
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
    private String buildPasswordParameters()
    {
        //getting password fields
        //////////////////////////////////////////////////////////
        String currentPass = ((EditText)findViewById(R.id.Password_CurrentPass_EditTex)).getText().toString();
        String newPass = ((EditText)findViewById(R.id.Password_newPassword_editText)).getText().toString();
        String confirmPass = ((EditText)findViewById(R.id.Password_confirmPassword_editText)).getText().toString();
        ////////////////////////////////////////////////////////////

        return "password=" + currentPass + "&newPassword=" + newPass + "&newPassword_confirmation=" + confirmPass;


    }
    private void showMessage(String message , String title)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(password.this).create();
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

}
