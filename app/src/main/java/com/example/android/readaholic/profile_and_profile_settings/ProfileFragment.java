package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.HomeFragment;
import com.example.android.readaholic.R;
import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;


public class ProfileFragment extends Fragment implements ProfileView{
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
    private ProfilePresenter mProfilePresenter;

    View view;
    volleyRequestHelper volleyRequestHelper;
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
        volleyRequestHelper = new volleyRequestHelper(view.getContext());

//        ButterKnife.bind(this,view);

        mProfilePresenter = new ProfilePresenter(view.getContext(),this);
        mRequestUrl = "https://api.myjson.com/bins/1dujwm";
        mProfileUser = mProfilePresenter.fetchData(mRequestUrl);

        //HTTPRequest(mRequestUrl,mProfileUser);

///////////////////////////////////////////////////////////////////////////////
        //Take user id when click in his name in Updates (user could be different from the current user)
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("UserId", mUser_Id);
        }
       // mProfileUser = new Users();

        UpdateData(mProfileUser);

        //Loading Fragments
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
     * function to return context of View
     * @return Context of view
     */
    @Nullable
    @Override
    public Context getViewContext() {
        return view.getContext();
    }

    /**
     * destory the view
     */
    @Override
    public void onDestroy() {
        mProfilePresenter.onDestroy();
        super.onDestroy();
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

    /**
     * implementation of UpdateDate from ProfileView interface.
     */
    @Override
    public void UpdateData(Users user) {

        Picasso.get().load(user.getmUserImageUrl()).transform(new CircleTransform()).into(mUserImage);
        mUserBookNumber.setText(user.getmUsernumberOfBooks()+" Books");
        mUserName.setText(user.getmUserName());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyRequestHelper = new volleyRequestHelper(getViewContext());
        mProfileUser = volleyRequestHelper.JsonObjectRequest("ShowProfile",null,null,0,false).get(0);

    }

}
