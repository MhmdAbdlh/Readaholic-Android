package com.example.android.readaholic.sign_in_up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.explore.ExploreActivity;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        //setting Signin button on click listener
        Button signIn = (Button)findViewById(R.id.Start_signIn_btn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(),SignIn.class);
                startActivity(intent);
            }
        });


        //setting SignUp button on click listener
        Button signUp = (Button)findViewById(R.id.Start_signUp_btn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent (v.getContext(),BookPageActivity.class);
                Intent intent = new Intent (v.getContext(),SignUp.class);
                startActivity(intent);
            }
        });

        //setting sign in as a user click listener
        TextView guest = (TextView)findViewById(R.id.Start_guest_TextView);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo.mIsGuest=true;
                Intent intent = new Intent(getBaseContext(), ExploreActivity.class);
                startActivity(intent);
            }
        });

        //setting forgot password on click listener
        TextView forgotPassword = (TextView)findViewById(R.id.Start_forgotPassword_TextView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });



    }

}