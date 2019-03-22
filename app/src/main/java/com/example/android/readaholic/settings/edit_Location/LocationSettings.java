package com.example.android.readaholic.settings.edit_Location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Countries;

public class LocationSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_settings);

        //setting and initializing the spinner
        Spinner countriesSpinner = (Spinner)findViewById(R.id.Location_spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,Countries.countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(adapter);

        setDefaultCountry();
        setDefaultPrivacy();




    }

    /**
     * Setting the default country shown when opening the activity
     * it should be the country that the user signed up with
     * if not it will be Afganistan(first country in alphab)
     */
    private void setDefaultCountry()
    {
        Spinner countriesSpinner = (Spinner)findViewById(R.id.Location_spinner);
        //setting the default selected country of the spinner
        Intent intent = getIntent();
        if(intent.hasExtra("location")){
            //getting the country sent from the previews activity
            String country = intent.getStringExtra("location");
            int index = Countries.getCountryIndex(country);
            //if the country exists in the stored countries -> make it default in the spinner
            if(index >= 0){
                countriesSpinner.setSelection(index);
            } else {
                //if the country doesnt exist in the stored countries set the defalt to afganistan
                countriesSpinner.setSelection(0);
            }

        }
    }

    /**
     * sets the default of who can see my location setting
     */
    private void setDefaultPrivacy()
    {
        Intent intent = getIntent();
        if(intent.hasExtra("whoCanSeeMyLocation")) {
            String whoCanSeeMyLocation = intent.getStringExtra("whoCanSeeMyLocation");
            switch (whoCanSeeMyLocation) {
                case "EVERYONE":
                    RadioButton everyOne = (RadioButton) findViewById(R.id.Location_everynone_RadioButton);
                    everyOne.setChecked(true);

                    break;
                case "ONLYME":
                    RadioButton onlyMe = (RadioButton) findViewById(R.id.Location_onlyMe_RadioButton);
                    onlyMe.setChecked(true);
            }
        }
    }
}
