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
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Countries;
import com.example.android.readaholic.settings.Settings;

import java.util.Calendar;

public class BirthdaySettings extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_settings);

        setClickListeners();
        setDefaultBirthDay();
        setDefaultPrivacy();
    }


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
    }
}
