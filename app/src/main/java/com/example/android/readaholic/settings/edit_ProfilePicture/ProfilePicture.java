package com.example.android.readaholic.settings.edit_ProfilePicture;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.settings.edit_Location.LocationSettings;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class ProfilePicture extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private boolean mImageSelected;
    private String mImagePath = "";
    private Bitmap mBitMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        //getting references
        ///////////////////////////////////////////////////////////////////////////
        mButtonChooseImage = findViewById(R.id.ProfilePicture_chooseImage_Button);
        mButtonUpload = findViewById(R.id.ProfilePicture_update_Button);
        mImageView = findViewById(R.id.ProfilePicture_imageView);
        mProgressBar = findViewById(R.id.ProfilePicture_progressBar);
        ////////////////////////////////////////////////////////////////////////////

        setClickListeners();
    }

    private void setClickListeners()
    {

        //choose image click listener
        //////////////////////////////////////////////////////////////////////////
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        ////////////////////////////////////////////////////////////////////////

        //update image click listener
        /////////////////////////////////////////////////////////////////////
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mImageSelected){
                    try {
                        changeImageRequest(imageToString(mBitMap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else
                    Toast.makeText(ProfilePicture.this, "Please select an image first", Toast.LENGTH_SHORT).show();

            }
        });
        ////////////////////////////////////////////////////////////////////

    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageSelected = true;
            mImageUri = data.getData();
            try {
                mBitMap = MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
            } catch (Exception e) {

            }
            Picasso.get().load(mImageUri).into(mImageView);
        } else {
            mImageSelected = false;
        }
    }

    //region requests
    /**
     * sending a request to save the location entered
     */
    private void changeImageRequest(String image) throws IOException {
        Urls urlController = new Urls(this,this.getBaseContext());
        RequestQueue queue = Volley.newRequestQueue(this);

        //converting selected image to string
        //String image = convertImageToFile(uri);

        String url = Urls.ROOT + Urls.CHANGE_IMAGE + "?" + urlController.constructTokenParameters()
                + "&Image=" +image ;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getBaseContext(), "Profile picture was updated successfully", Toast.LENGTH_LONG).show();
                        showLayout();
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
                    if (error.networkResponse.data != null) {
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
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                showLayout();
            }});



        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
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



    /**
     * showing the progress bar to indicate that request is in progress
     */
    private void Loading()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ProfilePicture_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ImageView image = (ImageView)findViewById(R.id.ProfilePicture_imageView);
        image.setVisibility(View.INVISIBLE);
    }

    /**
     * showing the original layout
     */
    private void showLayout()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ProfilePicture_progressBar);
        progressBar.setVisibility(View.GONE);

        ImageView image = (ImageView)findViewById(R.id.ProfilePicture_imageView);
        image.setVisibility(View.VISIBLE);


    }

    private String imageToString(Bitmap bitmap) {

       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG , 100 ,byteArrayOutputStream);
       byte[] imageByte = byteArrayOutputStream.toByteArray();
       return Base64.encodeToString(imageByte,Base64.DEFAULT);
    }

}
