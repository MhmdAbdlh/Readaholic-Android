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
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private JSONObject mJsonObject;
    private String mRequestUrl;
    private int mUser_Id;
    private Users mProfileUser;
    private ImageView mUserImage;
    private TextView mUserName;
    private TextView mUserBookNumber;
    public static int UserNumberofBooks;

    private String profileResponse = "{\n" +
            "  \"GoodreadsResponse\": {\n" +
            "    \"Request\": {\n" +
            "      \"authentication\": \"true\",\n" +
            "      \"key\": \"FLPIgg7i8q6SY3V8asRdw\",\n" +
            "      \"method\": \"user_show \"\n" +
            "    },\n" +
            "    \"user\": {\n" +
            "      \"id\": \"93870694\",\n" +
            "      \"name\": \"Hossam Ahmed\",\n" +
            "      \"link\": \"https://www.goodreads.com/user/show/93870694-hossam-ahmed\",\n" +
            "      \"image_url\": \"https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png\",\n" +
            "      \"small_image_url\": \"https://s.gr-assets.com/assets/nophoto/user/u_50x66-632230dc9882b4352d753eedf9396530.png\",\n" +
            "      \"location\": \"Cairo, 11, Egypt\",\n" +
            "      \"joined\": \"02/2019\",\n" +
            "      \"last_active\": \"03/2019\",\n" +
            "      \"favorite_authors\": \" \",\n" +
            "      \"updates_rss_url\": \"https://www.goodreads.com/user/updates_rss/93870694?key=QENImMDqYjOB3wjlwfRE2PZJVae1YU7fcLMoS_cd4keaZ6sM\",\n" +
            "      \"reviews_rss_url\": \"https://www.goodreads.com/review/list_rss/93870694?key=QENImMDqYjOB3wjlwfRE2PZJVae1YU7fcLMoS_cd4keaZ6sM&shelf=%23ALL%23\",\n" +
            "      \"friends_count\": {\n" +
            "        \"-type\": \"integer\",\n" +
            "        \"#text\": \"0\"\n" +
            "      },\n" +
            "      \"groups_count\": \"0\",\n" +
            "      \"reviews_count\": {\n" +
            "        \"-type\": \"integer\",\n" +
            "        \"#text\": \"7\"\n" +
            "      },\n" +
            "      \"user_shelves\": {\n" +
            "        \"-type\": \"array\",\n" +
            "        \"user_shelf\": [\n" +
            "          {\n" +
            "            \"id\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"305076705\"\n" +
            "            },\n" +
            "            \"name\": \"read\",\n" +
            "            \"book_count\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"2\"\n" +
            "            },\n" +
            "            \"exclusive_flag\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"description\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"sort\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"order\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"per_page\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"featured\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"recommend_for\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"sticky\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"305076703\"\n" +
            "            },\n" +
            "            \"name\": \"currently-reading\",\n" +
            "            \"book_count\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"2\"\n" +
            "            },\n" +
            "            \"exclusive_flag\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"description\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"sort\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"order\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"per_page\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"featured\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"recommend_for\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"sticky\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"305076701\"\n" +
            "            },\n" +
            "            \"name\": \"to-read\",\n" +
            "            \"book_count\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"3\"\n" +
            "            },\n" +
            "            \"exclusive_flag\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"description\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"sort\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"order\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"per_page\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"featured\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"recommend_for\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"sticky\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"updates\": {\n" +
            "        \"-type\": \"array\",\n" +
            "        \"#text\": \"...\"\n" +
            "      },\n" +
            "      \"user_statuses\": \" \"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__profile);

            mUserImage = (ImageView)findViewById(R.id.profileActivity_ProfilePic_ImageView);
            mUserName =(TextView)findViewById(R.id.ProfileActivity_UserName_TextView);
            mUserBookNumber = (TextView)findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);
            mRequestUrl = "https://api.myjson.com/bins/1dujwm";
            //HTTPRequest(mRequestUrl,mProfileUser);


            mProfileUser = new Users();

        JSONObject response = null;
        try {
            response = new JSONObject(profileResponse);
        } catch (JSONException e) {
            Log.e("First JSON OBj","Error in first json object");
        }
        JSONObject GoodReadsResponse = response.optJSONObject("GoodreadsResponse");
                JSONObject User = GoodReadsResponse.optJSONObject("user");
                mProfileUser.setmUserName(User.optString("name"));
                Log.e("Test" ,mProfileUser.getmUserName());
                mProfileUser.setmUserImageUrl(User.optString("image_url"));
                JSONObject user_shelves = User.optJSONObject("user_shelves");
                JSONArray user_shelf = user_shelves.optJSONArray("user_shelf");
                int numberOfBooks = 0;
                for(int i=0;i<3;i++)
                {
                    numberOfBooks += user_shelf.optJSONObject(i).optJSONObject("book_count").optInt("#text");
                }
                Log.e("number of book "," "+numberOfBooks);
                mProfileUser.setmUsernumberOfBooks(numberOfBooks);

                    Picasso.get().load(mProfileUser.getmUserImageUrl()).transform(new CircleTransform()).into(mUserImage);
                    mUserBookNumber.setText(mProfileUser.getmUsernumberOfBooks()+" Books");
                    mUserName.setText(mProfileUser.getmUserName());

        loadFragment(new books(),findViewById(R.id.Profile_Books_Fragment).getId());
        loadFragment(new Followers_fragment(),findViewById(R.id.Profile_Friends_Fragment).getId());
        loadFragment(new Updates_fragment(),findViewById(R.id.Profile_Updates_Fragment).getId());



    }



    public final class QueryUtils
    {
        public QueryUtils(){}

        public  int getBooksNumber()
        {
            return UserNumberofBooks;
        }

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
    private void HTTPRequest(String url, final Users Muser)
    {

        DiskBasedCache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               ExtractUser(response,Muser);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mJsonObject = null;
                Log.e("Volley ERROR","Error in volley request");

            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }

    /**
     * function to Extract data of user from json response
     * @param Response json object to root tree that contain the user data
     *
     */
    private void ExtractUser(JSONObject Response,Users mUser)
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
