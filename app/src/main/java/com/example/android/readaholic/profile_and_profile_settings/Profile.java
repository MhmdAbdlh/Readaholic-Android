package com.example.android.readaholic.profile_and_profile_settings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private JSONObject mJsonObject;
    private String mRequestUrl;
    private int mUser_Id;
    private Users mUser;
    private ImageView mUserImage;
    private TextView mUserName;
    private TextView mUserBookNumber;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__profile);
            loadFragment(new books(),findViewById(R.id.Profile_Books_Fragment).getId());
            loadFragment(new Followers_fragment(),findViewById(R.id.Profile_Friends_Fragment).getId());
            loadFragment(new Updates_fragment(),findViewById(R.id.Profile_Updates_Fragment).getId());

            mUserImage = (ImageView)findViewById(R.id.profileActivity_ProfilePic_ImageView);
            mUserName =(TextView)findViewById(R.id.ProfileActivity_UserName_TextView);
            mUserBookNumber = (TextView)findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);
            mRequestUrl = "https://api.myjson.com/bins/1dujwm";
            HTTPRequest(mRequestUrl);
       // Picasso.get().load(mUser.getmUserImageUrl()).into(mUserImage);
        mUserBookNumber.setText(mUser.getmUsernumberOfBooks()+" Books");
        mUserName.setText(mUser.getmUserName());

/*
            mUser = new Users();
            DiskBasedCache cache = new DiskBasedCache(getCacheDir(),1024*1024);
            BasicNetwork network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache,network);
            mRequestQueue.start();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                JSONObject GoodReadsResponse = response.optJSONObject("GoodreadsResponse");
                JSONObject User = GoodReadsResponse.optJSONObject("user");
                mUser.setmUserName(User.optString("name"));
                Log.e("Test" ,mUser.getmUserName());
                mUser.setmUserImageUrl(User.optString("image_url"));
                JSONObject user_shelves = User.optJSONObject("user_shelves");
                JSONArray user_shelf = user_shelves.optJSONArray("user_shelf");
                int numberOfBooks = 0;
                for(int i=0;i<3;i++)
                {
                    numberOfBooks += user_shelf.optJSONObject(i).optJSONObject("book_count").optInt("#text");
                }
                Log.e("number of book "," "+numberOfBooks);
                mUser.setmUsernumberOfBooks(numberOfBooks);



                mRequestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mJsonObject = null;
                mRequestQueue.stop();
            }
        });
        mRequestQueue.add(jsonObjectRequest);

*/



        }


        /**
         * loadFragment function to initialize the Fragment
         * @param fragment Fragment object to contain the layout.
         * @param ID id of the layout.
         * to initialize the Fragment argument and replace FrameLayout with it.
         * and id to assign it to certain fragment layout.
         */
        private void loadFragment(Fragment fragment,int ID) {
            // create a FragmentManager
            FragmentManager fm = getSupportFragmentManager();
            // create a FragmentTransaction to begin the transaction and replace the Fragment
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // replace the FrameLayout with new Fragment
            fragmentTransaction.add(ID, fragment);
            fragmentTransaction.commit(); // save the changes
        }

    /**
     * function to do the communication process giving url
     * @param url string url to determine the endpoint.
     *
     */
    private void HTTPRequest(String url)
    {

        DiskBasedCache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               ExtractUser(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mJsonObject = null;
                Log.e("Volly ERROR","Error in volley request");

            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }

    /**
     * function to Extract data of user from json response
     * @param Response json object to root tree that contain the user data
     *
     */
    private void ExtractUser(JSONObject Response)
    {
         mUser = new Users();
        JSONObject GoodReadsResponse = Response.optJSONObject("GoodreadsResponse");
        JSONObject User = GoodReadsResponse.optJSONObject("user");
        mUser.setmUserName(User.optString("name"));
        Log.e("Test" ,mUser.getmUserName());
        mUser.setmUserImageUrl(User.optString("small_image_url"));
        JSONObject user_shelves = User.optJSONObject("user_shelves");
        JSONArray user_shelf = user_shelves.optJSONArray("user_shelf");
        int numberOfBooks = 0;
        for(int i=0;i<3;i++)
        {
            numberOfBooks += user_shelf.optJSONObject(i).optJSONObject("book_count").optInt("#text");
        }
        Log.e("number of book "," "+numberOfBooks);
        mUser.setmUsernumberOfBooks(numberOfBooks);

    }


}
