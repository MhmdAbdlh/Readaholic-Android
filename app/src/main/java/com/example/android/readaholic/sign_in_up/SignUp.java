package com.example.android.readaholic.sign_in_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.readaholic.R;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setBackgroundDrawableResource(R.drawable.books_shelfes);
    }
}
