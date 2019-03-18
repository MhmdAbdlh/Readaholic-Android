package com.example.android.readaholic.sign_in_up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.readaholic.Main;
import com.example.android.readaholic.R;
import com.example.android.readaholic.profile_and_profile_settings.Profile;
import com.example.android.readaholic.books.BookPageActivity;
import com.example.android.readaholic.books.BookReviewsActivity;

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
                Intent intent = new Intent (v.getContext(),SignUp.class);
                startActivity(intent);
            }
        });

        Button signUP = (Button)findViewById(R.id.Start_signUp_btn);

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Profile.class);
                startActivity(intent);
            }
        });

    }


}
