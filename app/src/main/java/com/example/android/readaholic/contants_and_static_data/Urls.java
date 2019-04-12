package com.example.android.readaholic.contants_and_static_data;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.android.readaholic.R;

public class Urls extends AppCompatActivity {

    public static final String ROOT = "http://f8b0ca5b.ngrok.io";
    //Login url
    public static String LOG_IN = "/api/login";
    //sign up url
    public static String SIGN_UP = "/api/signUp";
    //show settings url
    public static String SHOW_SETTINGS = "/api/showSettings";
    //change birthday url
    public static String CHANGE_BIRTHDAY = "/api/changeBirthday";
    // who can see my birthday url
    public static String WHO_CAN_SEE_MY_BIRTHDAY = "/api/whoCanSeeMyBirthday";
    //change country url
    public static String CHANGE_COUNTRY = "/api/changeCountry";
    //who can see my country url
    public static String WHO_CAN_SEE_MY_COUNTRY = "/api/whoCanSeeMyCountry";



    public Urls(){}

    public String constructTokenParameters() {
        String parameters = "";

        parameters += "token=" + UserInfo.sToken + "&tokenType=" + UserInfo.sTokenType;

        return parameters;
    }

    //region login and signup

    public String getLogInParameters() {
        String parameters = "";

        //getting email and password entered by user
        String email = ((EditText)findViewById(R.id.SignUp_email_edittext)).getText().toString();
        String password = ((EditText)findViewById(R.id.SignIn_password_edittext)).getText().toString();


        //constructing the parameters
        parameters += "email=" + email + "&password=" + password;

        return parameters ;
    }

    public String getSignUpParameters() {
        String parameters = "";

        //getting data entered by user
        /*****************************************************************************************/
        String email = ((EditText)findViewById(R.id.SignUp_email_edittext)).getText().toString();
        String password = ((EditText)findViewById(R.id.SignUp_Pass_edittext)).getText().toString();
        String confPassword = ((EditText)findViewById(R.id.SignUp_rePass_edittext)).getText().toString();
        String birthday = ((Button)findViewById(R.id.SignUp_birthday_Button)).getText().toString();
        String location = ((Spinner)findViewById(R.id.SignUp_location_Spinner)).getSelectedItem().toString();
        String gender = ((Spinner)findViewById(R.id.SignUp_gender_Spinner)).getSelectedItem().toString();
        String firstName = ((EditText)findViewById(R.id.SignUp_firstName_edittext)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.SignUp_lastName_edittext)).getText().toString();

        //getting full name from first and last name
        String fullName = firstName + " " + lastName;

        /***************************************************************************************/



        //constructing the parameters
        parameters += "email=" + email +  "&fullName=" + fullName + "&password=" + password
                    + "&password_confirmation=" + confPassword + "&gender=" + gender
                    + "&location=" + location + "&birthday=" + birthday;

        return parameters ;
    }

    //endregion

    //region settings

    public  String getChangeBirthdayParameters() {
        String parameters = "";

        String birthday = ((Button)findViewById(R.id.Birthday_Button)).getText().toString();

        parameters += "birthday=" + birthday;

        return parameters;
    }

    public  String getWhoCanSeeMyBirthdayParameters() {
        String parameters = "";

        //getting the radio buttons for who can see my birth day
        /***************************************************************************************/
        RadioButton everyone = ((RadioButton)findViewById(R.id.Birthday_everyone_RadioButton));
        RadioButton onlyme = ((RadioButton)findViewById(R.id.Birthday_onlyMe_RadioButton));
        /***************************************************************************************/

        //checking which radio button is clicked
        /******************************************/
        String whoCanSeeMyBirthday ="";
        if(everyone.isChecked()) {
            whoCanSeeMyBirthday = "everyOne";
        } else {
            whoCanSeeMyBirthday = "onlyMe";
        }
        /*****************************************/


        //constructing the parameters
        parameters += "seeMyBirthday=" + whoCanSeeMyBirthday;

        return parameters;
    }

    public  String getChangeLocationParameters() {
        String parameters = "";

        String location = ((Spinner)findViewById(R.id.Location_spinner)).getSelectedItem().toString();

        parameters += "location=" + location;

        return parameters;
    }

    public String getWhoCanSeeMyLocationParameters() {
        String parameters = "";

        //getting the radio buttons for who can see my birth day
        /***************************************************************************************/
        RadioButton everyone = ((RadioButton)findViewById(R.id.Location_everynone_RadioButton));
        RadioButton onlyme = ((RadioButton)findViewById(R.id.Location_onlyMe_RadioButton));
        /***************************************************************************************/

        //checking which radio button is clicked
        /******************************************/
        String whoCanSeeMyLocation ="";
        if(everyone.isChecked()) {
            whoCanSeeMyLocation = "everyOne";
        } else {
            whoCanSeeMyLocation = "onlyMe";
        }
        /*****************************************/


        //constructing the parameters
        parameters += "seeMyCountry=" + whoCanSeeMyLocation;

        return parameters;
    }






    //endregion



}
