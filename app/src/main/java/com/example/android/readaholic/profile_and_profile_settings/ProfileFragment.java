package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private RequestQueue mRequestQueue;
    private String mRequestUrl;
    private int mUser_Id;
    private  Users mProfileUser;
    private ImageView mUserImage;
    private TextView mUserName;
    private TextView mUserBookNumber;
    private TextView profileSettingtofollowingState;
    private ImageView profilerEditToRightSign;

    ArrayList<Updates> arayOfUpdates = new ArrayList<Updates>();
    private ListView mListOfUpdates;
    private UpdatesAdapter adapterForUpdatesList;
    private int userFollowingState;
    View view;
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
        view = inflater.inflate(R.layout.profile_fragment,container,false);
        mUserImage = (ImageView)view.findViewById(R.id.profileActivity_ProfilePic_ImageView);
        mUserName =(TextView)view.findViewById(R.id.ProfileActivity_UserName_TextView);
        mUserBookNumber = (TextView)view.findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);
        profilerEditToRightSign = (ImageView)view.findViewById(R.id.Profile_Settings_ImageView);
        profileSettingtofollowingState = (TextView)view.findViewById(R.id.Profile_Settings_TextView);
///////////////////////////////////////////////////////////////////////////////
        //Take user id when click in his name in Updates (user could be different from the current user)
        /*Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("UserId", mUser_Id);
        }
*/
        ///////////////////////////////////////////////////////////////////////////////////////////////
       /* arayOfUpdates = HomeFragment.onResposeAction(jsonFile);
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
*/
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            if (getArguments() == null)
                mUser_Id = 0;
            else {
                mUser_Id = getArguments().getInt("user-id");
                userFollowingState = getArguments().getInt("followingState");
            }
            Log.e("userid", Integer.toString(mUser_Id));
            requestProfileView(mUser_Id);
        }

    }

    /**
     * loadFragment function to initialize the Fragment
     * @param fragment Fragment object to contain the layout.
     * @param ID id of the layout.
     * to initialize the Fragment argument and replace FrameLayout with it.
     * and id to assign it to certain fragment layout.
     */
    private void loadFragment(Fragment fragment,int ID,int userID) {
        // create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("user-id",userID);
        if(ID == view.findViewById(R.id.Profile_Books_Fragment).getId())
            bundle.putInt("books-num",mProfileUser.getmUsernumberOfBooks());
        else if(ID == view.findViewById(R.id.Profile_Friends_Fragment).getId())
        {
            bundle.putInt("followers-num",mProfileUser.getmNumberOfFollowers());
            bundle.putInt("followers-num",mProfileUser.getGetmNumberOfFolloweings());
        }
        fragment.setArguments(bundle);
        // replace the FrameLayout with new Fragment
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    /**
     *Update the view with data of user
     * @param user holding data from request.
     */
    public void UpdateData(Users user ,int id) {
        if(id != 0)
        {
            if(userFollowingState == 1)//user is following this profile.
                {
                    profileSettingtofollowingState.setText(" FOLLOWING");
                    profilerEditToRightSign.setImageResource(R.drawable.check);
                }
                else //user is not following this profile user.
                    {
                        profileSettingtofollowingState.setText(" FOLLOW");
                        profilerEditToRightSign.setImageResource(R.drawable.check);
                    }

        }
        else
        {

        }
        Log.e("UserImageUrl",user.getmUserImageUrl());
            Picasso.get().load(user.getmUserImageUrl()).transform(new CircleTransform()).into(mUserImage);
        mUserBookNumber.setText(user.getmUsernumberOfBooks()+" Books");
        mUserName.setText(user.getmUserName());

    }

    /**
     * function to doing the request to get user info and update the view with it.
     */
    public void requestProfileView(final int user_id)
{
    if(user_id != 0)
    mRequestUrl =Urls.ROOT + "/api/showProfile?"+"id="+Integer.toString(user_id)+"&token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

    else
        mRequestUrl = Urls.ROOT  + "/api/showProfile?"+"token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

    mProfileUser = new Users();
    DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
    BasicNetwork network = new BasicNetwork(new HurlStack());
    mRequestQueue = new RequestQueue(cache, network);
    mRequestQueue.start();
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject Response) {
            ExtractUser(Response);
            UpdateData(mProfileUser,user_id);

            //Loading Fragments
            loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId(),mUser_Id);
            loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId(),mUser_Id);
            loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId(),mUser_Id);

            mRequestQueue.stop();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {


            mProfileUser=null;
            mRequestQueue.stop();
        }
    });

    mRequestQueue.add(jsonObjectRequest);
}

    /**
     * extract user info from response.
     * @param Response
     * @return user object holding the user data.
     */
    public Users ExtractUser(JSONObject Response)
{
    Log.e("profileResponse",Response.toString());
    mProfileUser.setmUserName(Response.optString("name"));
    Log.e("Test" ,mProfileUser.getmUserName());
    mProfileUser.setmUserImageUrl(Response.optString("small_image_link"));
    mProfileUser.setmUsernumberOfBooks(Response.optInt("books_count"));
    mProfileUser.setmNumberOfFollowers(Response.optInt("followers_count"));
    mProfileUser.setGetmNumberOfFolloweings(Response.optInt("following_count"));
    Log.e("showprofileResponseName",mProfileUser.getmUserName());

    return mProfileUser;

}
}
