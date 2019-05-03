package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * class FollowersTab Fragment of the tabbed fragment
 * @author Hossam ahmed
 */
public class Followers_fragment extends Fragment {

     RecyclerView recyclerView;
     FollowersAdapter mAdapter;
     RecyclerView.LayoutManager layoutManager;
     List<String> mUsers;
     TextView mNotAvaliable ;
     RequestQueue mRequestQueue;
     View view;
     int userId;
     int FollowingNumber;
    int FollowersNumber;
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


    /**
     * onCreateView called when the view is created
     * @param inflater inflate the layout
     * @param container parent view
     * @param savedInstanceState bundle of saved states
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_followers_fragment, container, false);
        //make the TextView of not available message at first invisible.

        mNotAvaliable =(TextView)view.findViewById(R.id.NotAvaliableTextView);
        mNotAvaliable.setVisibility(View.INVISIBLE);


        Bundle bundle = getArguments();
        userId = bundle.getInt("user-id");
        FollowingNumber = bundle.getInt("following-num");
        FollowersNumber = bundle.getInt("followers-num");

        Log.e("FollowersFragment","followers: "+Integer.toString(FollowersNumber)+" followings: "+Integer.toString(FollowingNumber));





        TextView followersTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowersNumber_TextView);
        followersTextView.setText(Integer.toString(FollowersNumber)+" FOLLOWERS");
        followersTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment FollowersAndFollowing = new FollowersAndFollowingFragment();
                Bundle bundle =new Bundle();
                bundle.putInt("section_number",1);
                bundle.putInt("user-id",userId);
                bundle.putInt("following-num",FollowingNumber);
                bundle.putInt("followers-num",FollowersNumber);
                FollowersAndFollowing.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ProfileLayout,FollowersAndFollowing,"FollowersFragment").addToBackStack("FollowersToFollowersAndFollowing").commit();
            }

        });
        TextView followingsTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowingNumber_TextView);
        followingsTextView.setText(Integer.toString(FollowingNumber)+" FOLLOWINGS");
        followingsTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment FollowersAndFollowing = new FollowersAndFollowingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("section_number", 0);
                bundle.putInt("user-id",userId);
                bundle.putInt("following-num",FollowingNumber);
                bundle.putInt("followers-num",FollowersNumber);
                FollowersAndFollowing.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ProfileLayout, FollowersAndFollowing,"FollowersFragment").addToBackStack("FollowersToFollowersAndFollowing").commit();
            }
        });
        ExtractFollowings(userId);
        return view;
    }

    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }



    /**
     * function to do the communication process
     */
    private void ExtractFollowings(int id)
    {
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(),1024*1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache,network);
        mRequestQueue.start();
        final String mRequestUrl;
        if(userId == 0) {
            Log.e("followingsInProfile","user id is sent equal to 0");
            mRequestUrl =Urls.ROOT + "/api/following?" + "token=" +
                    UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        }
        else

            {
                Log.e("followingsInProfile","user id is sent equal to  "+Integer.toString(userId));
                mRequestUrl =Urls.ROOT + "/api/following?user_id="+Integer.toString(userId) + "&token=" +
                        UserInfo.sToken + "&type=" + UserInfo.sTokenType;
                Log.e("FollowingInProfile",mRequestUrl);
            }

        mUsers = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("FollowingInProfile", "user id is "+Integer.toString(userId));
                Log.e("FollowingInProfileRes",response.toString());


                JSONArray followings = response.optJSONArray("following");
                if (followings == null) {
                    mUsers = null ;
                } else {
                    for (int i = 0; i < followings.length(); i++) {
                        String userImageUrl = null;
                        userImageUrl =(followings.optJSONObject(i).optString("image_link"));
                        mUsers.add(userImageUrl);
                    }
                    UpdateList();
                }

                mRequestQueue.stop();

            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUsers = null;
                Log.e("Volly ERROR","Error in volley request");

            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }
private void UpdateList()
{
    if(mUsers.isEmpty())
    {
        Log.e("not available ","not available in following and followers ");
        mNotAvaliable.setVisibility(View.VISIBLE);
    }
    else {

        recyclerView = (RecyclerView) view.findViewById(R.id.FollowersFragment_FollowersList_RecyclerView);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //       recyclerView.setHasFixedSize(true);

        // specify an adapter
        mAdapter = new FollowersAdapter(getContext(),mUsers);
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

    }


}
}
