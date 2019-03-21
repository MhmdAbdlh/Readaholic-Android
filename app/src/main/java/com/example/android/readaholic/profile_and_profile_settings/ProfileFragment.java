package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.HomeFragment;
import com.example.android.readaholic.R;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private RequestQueue mRequestQueue;
    private JSONObject mJsonObject;
    private String mRequestUrl;
    private int mUser_Id;
    private Users mProfileUser;
    private ImageView mUserImage;
    private TextView mUserName;
    private TextView mUserBookNumber;
    public static int UserNumberofBooks;
    ArrayList<Updates> arayOfUpdates = new ArrayList<Updates>();
    private ListView mListOfUpdates;
    private UpdatesAdapter adapterForUpdatesList;

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

    private String jsonFile = "{\n" +
            "   \"updates\":{\n" +
            "      \"update\":[\n" +
            "         {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "            \"numComments\":\"11\",\n" +
            "            \"numLikes\":\"77\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"5\",\n" +
            "               \"type\":\"0\",\n" +
            "               \"rating\":\"5\",\n" +
            "               \"body\":\"\",\n" +
            "               \"book\":{\n" +
            "                  \"id\":\"31087\",\n" +
            "                  \"title\":\"American Duchess\",\n" +
            "\t\t\t\t  \"author\":\"Karen Harper\",\n" +
            "                  \"imgUrl\":\"https://i.harperapps.com/covers/9780062884299/y648.jpg\",\n" +
            "                  \"shelf\":\"\",\n" +
            "                  \"rating\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "\t\t {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "            \"numComments\":\"11\",\n" +
            "            \"numLikes\":\"9\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"5\",\n" +
            "               \"type\":\"0\",\n" +
            "               \"rating\":\"0\",\n" +
            "\t\t\t   \"review\":\"salma\",\n" +
            "               \"body\":\"\",\n" +
            "               \"book\":{\n" +
            "                  \"id\":\"31087\",\n" +
            "                  \"title\":\"American Duchess\",\n" +
            "\t\t\t\t  \"author\":\"Karen Harper\",\n" +
            "                  \"imgUrl\":\"https://i.harperapps.com/covers/9780062884299/y648.jpg\",\n" +
            "                  \"shelf\":\"want to read\",\n" +
            "                  \"rating\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":\"000001\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "            \"numComments\":\"7\",\n" +
            "            \"numLikes\":\"9\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"\",\n" +
            "               \"type\":\"2\",\n" +
            "               \"user\":{\n" +
            "                  \"name\":\"salma\",\n" +
            "                  \"imageLink\":\"\",\n" +
            "                  \"ratingAvg\":\"\",\n" +
            "                  \"ratingCount\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "            \"numComments\":\"2\",\n" +
            "            \"numLikes\":\"12\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"5\",\n" +
            "               \"type\":\"1\",\n" +
            "               \"shelf\":\"wants to read\",\n" +
            "               \"book\":{\n" +
            "                  \"id\":\"31087\",\n" +
            "                  \"title\":\"The Last Boleyn\",\n" +
            "                  \"imgUrl\":\"\",\n" +
            "\t\t\t\t  \"author\":\"karen\",\n" +
            "                  \"shelf\":\"\",\n" +
            "                  \"rating\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "                  \"numComments\":\"127\",\n" +
            "                  \"numLikes\":\"6123\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"7\",\n" +
            "               \"type\":\"3\",\n" +
            "               \"resourceType\":\"1\",\n" +
            "\t\t\t\t\t \"comment\":\"the best book ever <3\",\n" +
            "               \"update\":{\n" +
            "                  \"id\":\"0000000\",\n" +
            "                  \"actor\":{\n" +
            "                     \"id\":\"65993249\",\n" +
            "                     \"name\":\"Salma Ibrahim\",\n" +
            "                     \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "                  },\n" +
            "                  \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "                  \"action\":{\n" +
            "                     \"id\":\"5\",\n" +
            "                     \"type\":\"1\",\n" +
            "                     \"shelf\":\"wants to read\",\n" +
            "                     \"book\":{\n" +
            "                        \"id\":\"31087\",\n" +
            "\t\t\t\t\t\t\"author\":\"Karen\",\n" +
            "                        \"title\":\"The Last Boleyn\",\n" +
            "                        \"imgUrl\":\"\",\n" +
            "                        \"shelf\":\"\",\n" +
            "                        \"rating\":\"\"\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "\t\t {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "                  \"numComments\":\"127\",\n" +
            "                  \"numLikes\":\"6123\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"7\",\n" +
            "               \"type\":\"3\",\n" +
            "               \"resourceType\":\"1\",\n" +
            "\t\t\t\t\t \"comment\":\"the best book ever <3\",\n" +
            "               \"update\":{\"id\":\"000001\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "            \"numComments\":\"7\",\n" +
            "            \"numLikes\":\"9\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"\",\n" +
            "               \"type\":\"2\",\n" +
            "               \"user\":{\n" +
            "                  \"name\":\"Farah\",\n" +
            "                  \"imageLink\":\"\",\n" +
            "                  \"ratingAvg\":\"\",\n" +
            "                  \"ratingCount\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma Ibrahim\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "            \"numComments\":\"5\",\n" +
            "            \"numLikes\":\"9\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"7\",\n" +
            "               \"type\":\"4\",\n" +
            "\t\t\t   \"comment\":\"the best book\",\n" +
            "               \"resourceType\":\"0\",\n" +
            "               \"body\":\"\",\n" +
            "               \"update\":{\n" +
            "                  \"id\":\"0000000\",\n" +
            "                  \"actor\":{\n" +
            "                     \"id\":\"65993249\",\n" +
            "                     \"name\":\"Salma Ibrahim\",\n" +
            "                     \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "                  },\n" +
            "                  \"updated_at\":\"Fri, 08 Mar 2019 04:16:55 -0800\",\n" +
            "                  \"action\":{\n" +
            "                     \"id\":\"5\",\n" +
            "                     \"type\":\"0\",\n" +
            "                     \"rating\":\"3\",\n" +
            "                     \"body\":\"\",\n" +
            "                     \"book\":{\n" +
            "                        \"id\":\"31087\",\n" +
            "                        \"title\":\"The Last Boleyn\",\n" +
            "\t\t\t\t\t\t\"author\":\"Harry\",\n" +
            "                        \"imgUrl\":\"\",\n" +
            "                        \"shelf\":\"\",\n" +
            "                        \"rating\":\"\"\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "         }, \n" +
            "\t\t {\n" +
            "            \"id\":\"0000000\",\n" +
            "            \"actor\":{\n" +
            "               \"id\":\"65993249\",\n" +
            "               \"name\":\"Salma\",\n" +
            "               \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "            },\n" +
            "            \"updated_at\":\"sat at 7:00AM\",\n" +
            "            \"numComments\":\"5\",\n" +
            "            \"numLikes\":\"9\",\n" +
            "            \"action\":{\n" +
            "               \"id\":\"7\",\n" +
            "               \"type\":\"4\",\n" +
            "\t\t\t   \"comment\":\"the best book\",\n" +
            "               \"resourceType\":\"0\",\n" +
            "               \"body\":\"\",\n" +
            "               \"update\":{\n" +
            "                  \"id\":\"0000000\",\n" +
            "                  \"actor\":{\n" +
            "                     \"id\":\"65993249\",\n" +
            "                     \"name\":\"Salma\",\n" +
            "                     \"imageLink\":\"https://images.gr-assets.com/users/1489660298p2/65993249.jpg\"\n" +
            "                  },\n" +
            "                  \"updated_at\":\"Fri at 8:00PM\",\n" +
            "                  \"action\":{\n" +
            "                     \"id\":\"5\",\n" +
            "                     \"type\":\"0\",\n" +
            "                     \"rating\":\"0\",\n" +
            "\t\t\t\t\t \"review\":\"the best book ever\",\n" +
            "                     \"body\":\"\",\n" +
            "                     \"book\":{\n" +
            "                        \"id\":\"31087\",\n" +
            "                        \"title\":\"The Last Boleyn\",\n" +
            "\t\t\t\t\t\t\"author\":\"Harry\",\n" +
            "                        \"imgUrl\":\"\",\n" +
            "                        \"shelf\":\"\",\n" +
            "                        \"rating\":\"\"\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "\t\t }\n" +
            "      ],\n" +
            "      \"_type\":\"array\"\n" +
            "   }\n" +
            "}";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        mUserImage = (ImageView)view.findViewById(R.id.profileActivity_ProfilePic_ImageView);
        mUserName =(TextView)view.findViewById(R.id.ProfileActivity_UserName_TextView);
        mUserBookNumber = (TextView)view.findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);


        mRequestUrl = "https://api.myjson.com/bins/1dujwm";
        //HTTPRequest(mRequestUrl,mProfileUser);

///////////////////////////////////////////////////////////////////////////////
        //Take user id when click in his name in Updates (user could be different from the current user)
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("UserId", mUser_Id);
        }
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


        loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId());
        loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId());
        loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId());
        ////////////////////////////////////////////////////////////////////////////////////////////////
        arayOfUpdates = HomeFragment.onResposeAction(jsonFile);
        adapterForUpdatesList = new UpdatesAdapter(getContext(),arayOfUpdates);
        mListOfUpdates = (ListView) view.findViewById(R.id.Profile_updateslist_listview);
        mListOfUpdates.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        mListOfUpdates.setAdapter(adapterForUpdatesList);

        return view;

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
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit(); // save the changes
    }


    //   /**
    //* function to do the communication process giving url
    //* @param url string url to determine the endpoint.
    //*
    //*/
    /*private void HTTPRequest(String url, final Users Muser)
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

  //  /**
    // * function to Extract data of user from json response
    // * @param Response json object to root tree that contain the user data
     //*
     //*/
   /* private void ExtractUser(JSONObject Response,Users mUser)
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

*/
}
