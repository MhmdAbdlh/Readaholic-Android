package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.readaholic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Followingtab_Fragment extends Fragment {
     List<Users> mUser;
     TextView mNotAvaliableTextView;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManger;
     FollowingLiastAdapter mAdapter;
    int FollowingNumber;
    String FollowesResponse = "{\n" +
            "  \"GoodreadsResponse\": {\n" +
            "    \"Request\": {\n" +
            "      \"authentication\": \"false\",\n" +
            "      \"method\": \"\"\n" +
            "    },\n" +
            "    \"following\": {\n" +
            "      \"-start\": \"1\",\n" +
            "      \"-end\": \"2\",\n" +
            "      \"-total\": \"2\",\n" +
            "      \"user\": [\n" +
            "        {\n" +
            "          \"id\": \"27948863\",\n" +
            "          \"name\": \"Ahmed Mahmoud\",\n" +
            "          \"link\": \"https://www.goodreads.com/user/show/27948863-ahmed-mahmoud\",\n" +
            "          \"image_url\": \"https://images.gr-assets.com/users/1551035887p3/27948863.jpg\",\n" +
            "          \"small_image_url\": \"https://images.gr-assets.com/users/1551035887p2/27948863.jpg\",\n" +
            "          \"friends_count\": \"27\",\n" +
            "          \"reviews_count\": \"8\",\n" +
            "          \"created_at\": \"Thu Mar 14 11:30:28 -0700 2019\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"7004371\",\n" +
            "          \"name\": \"Kevin Emerson\",\n" +
            "          \"link\": \"https://www.goodreads.com/user/show/7004371-kevin-emerson\",\n" +
            "          \"image_url\": \"https://images.gr-assets.com/users/1507144891p3/7004371.jpg\",\n" +
            "          \"small_image_url\": \"https://images.gr-assets.com/users/1507144891p2/7004371.jpg\",\n" +
            "          \"friends_count\": \"361\",\n" +
            "          \"reviews_count\": \"72\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.followingtab_fragment,container,false);

        mNotAvaliableTextView = (TextView) view.findViewById(R.id.Followingtab_fragment_NotAvaliableTextView);
        mNotAvaliableTextView.setVisibility(View.INVISIBLE);

        //TextView FollowingNumber = (TextView)view.findViewById(R.id.Followingtab_fragment_Followings_TextView);
        //FollowingNumber.setText(FollowingNumber+" Following");

        if(mUser==null)
        {
            mNotAvaliableTextView.setVisibility(View.VISIBLE);
        }
        else {

            mRecyclerView = (RecyclerView) view.findViewById(R.id.Followingtab_fragment_FollowingsList_RecyclerView);

            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView.setLayoutManager(mLayoutManger);


            // specify an adapter
            mAdapter = new FollowingLiastAdapter(getContext(),mUser);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));

*/


        JSONObject response = null;
        try {
            response = new JSONObject(FollowesResponse);
        } catch (JSONException e) {
            Log.e("First JSON OBj","Error in first json object");
        }
        JSONObject GoodReadsResponse = response.optJSONObject("GoodreadsResponse");
        JSONObject Following = GoodReadsResponse.optJSONObject("following");
        JSONArray User = null;
        try {
            User = Following.getJSONArray("user");
        } catch (JSONException e) {
            Log.e("JsonARRRAY ERROR","error in json array user");
        }
        mUser = new ArrayList<>();
        Users user;
        FollowingNumber = User.length();
        for(int i=0;i<User.length();i++){
            try {
                String image = User.getJSONObject(i).optString("image_url");
                String Name = User.getJSONObject(i).optString("name");
                int numberofbooks = User.getJSONObject(i).optInt("reviews_count");
                mUser.add(new Users(Name,image,numberofbooks));
            } catch (JSONException e) {
                Log.e("User Error","ERRRRRRRORRRRRRRRRRRRRR");
            }

        }

    }
}
