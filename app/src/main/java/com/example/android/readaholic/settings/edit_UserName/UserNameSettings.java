package com.example.android.readaholic.settings.edit_UserName;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Countries;

public class UserNameSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_settings);

        setDefaultUserName();
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
}
