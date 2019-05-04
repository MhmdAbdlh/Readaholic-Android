package com.example.android.readaholic.profile_and_profile_settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.example.android.readaholic.settings.Settings;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@VisibleForTesting
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
    private static ProgressDialog mProgressDialog;
    private View upperSettings;
    private View belowSettings;
    ArrayList<Updates> arayOfUpdates = new ArrayList<Updates>();
    private ListView mListOfUpdates;
    private UpdatesAdapter adapterForUpdatesList;
    private int userFollowingState;
    View view;
    ProgressBar progressBar;
    RelativeLayout settingsLayout;
    LinearLayout ProfileContainer;
    String authUserShowProfileResponse="{\"id\":1,\"name\":\"test\",\"username\":\"test\",\"email\":\"test@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"female\",\"country\":\"Canada\",\"city\":\"Atawwa\",\"joined_at\":\"2019-05-03\",\"followers_count\":-2,\"following_count\":-1,\"rating_avg\":5,\"rating_count\":21,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":null,\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null}";
    String UserShowProfileResponse="{\"id\":5,\"name\":\"Salma\",\"username\":\"Salma\",\"email\":\"Salma@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"female\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"joined_at\":\"2019-05-03\",\"followers_count\":0,\"following_count\":0,\"rating_avg\":0,\"rating_count\":0,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":\"eyJpdiI6ImV3Qjlqb2cyVjh3UG1FbHRDS0F2YWc9PSIsInZhbHVlIjoiSVQ4elQwVVZ6YzlMOStrclJaTHBoQT09IiwibWFjIjoiMDdiNTQyY2MyMzkxMzE4ZjU2ODkyMzJiNmI5NjlmOTA4ZGUzZWI1MzQ4YTQ3YjQzODhjYmJhNzEzMzEwNGVkNCJ9\",\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null,\"is_followed\":1}";
    String UserShowProfileResponse3="{\"id\":3,\"name\":\"waleed\",\"username\":\"waleed\",\"email\":\"waleed@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"joined_at\":\"2019-05-03\",\"followers_count\":-1,\"following_count\":0,\"rating_avg\":0,\"rating_count\":0,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":null,\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null,\"is_followed\":0}";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment,container,false);
        mUserImage = (ImageView)view.findViewById(R.id.profileActivity_ProfilePic_ImageView);
        mUserName =(TextView)view.findViewById(R.id.ProfileActivity_UserName_TextView);
        belowSettings =(View)view.findViewById(R.id.belowSetting);
        ProfileContainer = (LinearLayout)view.findViewById(R.id.ProfileContainer);
        progressBar=(ProgressBar)view.findViewById(R.id.Profile_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mUser_Id = bundle.getInt("user-id");
        }

        //Loading Fragments
      // loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId(),mUser_Id);
      // loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId(),mUser_Id);
        loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId(),mUser_Id);

        mUserBookNumber = (TextView)view.findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);
        profilerEditToRightSign = (ImageView)view.findViewById(R.id.Profile_Settings_ImageView);
        profileSettingtofollowingState = (TextView)view.findViewById(R.id.Profile_Settings_TextView);
        settingsLayout = (RelativeLayout)view.findViewById(R.id.Profile_SeetingsLayout);

        bundle = this.getArguments();
        if (bundle != null) {
            mUser_Id = bundle.getInt("UserId");
        }

        if(savedInstanceState == null) {
            if (getArguments() == null)
                mUser_Id = 0;
            else {
                mUser_Id = getArguments().getInt("user-id");
               // userFollowingState = getArguments().getInt("followingState");
            }
            Log.e("userid", Integer.toString(mUser_Id));
            requestProfileView(mUser_Id);
        }

        profileSettingtofollowingState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!UserInfo.IsMemic)
                {
                    if(mUser_Id !=0){
                        if(userFollowingState == 1)//the user is following u.
                        {
                            userFollowingState =0;//the user  un-follow u.
                            unfollowUser(mUser_Id);
                            profileSettingtofollowingState.setText("FOLLOW");
                        }
                        else if(userFollowingState ==0)
                        {
                            userFollowingState =1;//the user follow u.
                            followUser(mUser_Id);
                            profileSettingtofollowingState.setText("FOLLOWING");
                        }

                    }
                    else
                    {
                        Intent intent = new Intent(getContext(), Settings.class);
                        startActivity(intent);
                    }
                }
                else
                    {
                        if(mUser_Id !=0){
                            if(userFollowingState == 1)//the user is following u.
                            {
                                userFollowingState =0;//the user  un-follow u.
                                profileSettingtofollowingState.setText("FOLLOW");
                            }
                            else if(userFollowingState ==0)
                            {
                                userFollowingState =1;//the user follow u.
                                profileSettingtofollowingState.setText("FOLLOWING");
                            }

                        }
                        else
                        {
                            Intent intent = new Intent(getContext(), Settings.class);
                            startActivity(intent);
                        }
                    }
            }

        });

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        bundle.putInt("user-id",mUser_Id);
        if(ID == view.findViewById(R.id.Profile_Books_Fragment).getId()) {
            bundle.putInt("books-num", mProfileUser.getmUsernumberOfBooks());
        }
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
        progressBar.setVisibility(View.GONE);
        ProfileContainer.setVisibility(View.VISIBLE);
        if(UserInfo.mIsGuest ==false) {
            if (mUser_Id != 0) {

                if (userFollowingState == 1)//user is following this profile.
                {
                    profileSettingtofollowingState.setText(" FOLLOWING");
                    profilerEditToRightSign.setImageResource(R.drawable.check);
                } else //user is not following this profile user.
                {
                    profileSettingtofollowingState.setText(" FOLLOW");
                    profilerEditToRightSign.setImageResource(R.drawable.check);
                }

            } else {

            }
        }
        else
            {
                belowSettings.setVisibility(View.GONE);
                settingsLayout.setVisibility(View.GONE);
            }
        final AtomicBoolean loaded = new AtomicBoolean();
        Picasso.get().load(user.getmUserImageUrl()).transform(new CircleTransform()).into(mUserImage, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                loaded.set(true);
            }
        });
        if (!loaded.get()) {
            // The image was immediately available.
            Picasso.get().
                    load("https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png").
                    transform(new CircleTransform()).into(mUserImage);
        }

        mUserBookNumber.setText(user.getmUsernumberOfBooks()+" Books");
        Log.e("UserNameInProfile",user.getmUserName());

        mUserName.setText(user.getmUserName());

    }

    /**
     * function to doing the request to get user info and update the view with it.
     */
    public void requestProfileView(final int user_id)
{

    if(mUser_Id != 0)
    mRequestUrl =Urls.ROOT + "/api/showProfile?"+"id="+Integer.toString(mUser_Id)+"&token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

    else
        mRequestUrl = Urls.ROOT  + "/api/showProfile?"+"token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

    mProfileUser = new Users();
    if(!UserInfo.IsMemic)
    {
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
            loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId(),user_id);
            loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId(),user_id);
            //loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId(),user_id);

            mRequestQueue.stop();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            //Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
           // mProfileUser=null;

            mProfileUser=null;
            mRequestQueue.stop();
        }
    });

    mRequestQueue.add(jsonObjectRequest);
}
    else
        {
            JSONObject Response = null;
            if(mUser_Id==0) {
                try {
                    Response = new JSONObject(authUserShowProfileResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if(mUser_Id==5)
                {
                    try {
                        Response = new JSONObject(UserShowProfileResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            else if(mUser_Id==3)
            {
                try {
                    Response = new JSONObject(UserShowProfileResponse3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            ExtractUser(Response);
            UpdateData(mProfileUser,user_id);

            //Loading Fragments
            loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId(),user_id);
            loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId(),user_id);
            //loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId(),user_id);

        }
}

    /**
     * extract user info from response.
     * @param Response
     * @return user object holding the user data.
     */
    public Users ExtractUser(JSONObject Response)
{
    Users users = new Users();
    Log.e("profileResponse",Response.toString());
    users.setmUserName(Response.optString("name"));
    Log.e("Test" ,users.getmUserName());
    users.setmUserImageUrl(Response.optString("image_link"));
    users.setmUsernumberOfBooks(Response.optInt("books_count"));
    users.setmNumberOfFollowers(Response.optInt("followers_count"));
    users.setGetmNumberOfFolloweings(Response.optInt("following_count"));
    userFollowingState=Response.optInt("is_followed");
    Log.e("showprofileResponseName",users.getmUserName());
    mProfileUser = users;
    return users;

}

    boolean followUser(int userId) {
        mRequestUrl = Urls.ROOT + "/api/follow?" + "user_id=" + Integer.toString(userId) + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        Log.e("followUserUrl",mRequestUrl);
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        final boolean[] status = new boolean[1];
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mRequestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject Response = null;
                try {
                    Response = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                status[0] = Response.optBoolean("status");
                Toast.makeText(getContext(),Response.optString("message"),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                status[0] = false;
            }
        });
        mRequestQueue.add(stringRequest);

        return status[0];
    }
    boolean unfollowUser(int userId) {
        mRequestUrl = Urls.ROOT + "/api/unfollow?" + "user_id=" + Integer.toString(userId) + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        final boolean[] status = new boolean[1];
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, mRequestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject Response = null;
                try {
                    Response = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                status[0] = Response.optBoolean("status");
                Toast.makeText(getContext(),Response.optString("message"),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                status[0] = false;
            }
        });
        mRequestQueue.add(stringRequest);
        return status[0];
    }


}
