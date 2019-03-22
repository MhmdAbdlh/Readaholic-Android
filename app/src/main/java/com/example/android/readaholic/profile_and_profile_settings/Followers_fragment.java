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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.R;

import org.json.JSONArray;
import org.json.JSONException;
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
     ImageLoader mImageLoader;
     RequestQueue mRequestQueue;
     View view;
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



        //TextView FollowingNumber = (TextView)view.findViewById(R.id.FollowersFragment_FollowingNumber_TextView);
        //FollowingNumber.setText(FollowingNumber +" Following");

/*
        String url = "https://api.myjson.com/bins/fjksu";
        HTTPRequest(url);
       */

        TextView followersTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowersNumber_TextView);
        followersTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment FollowersAndFollowing = new FollowersAndFollowingFragment();
                Bundle bundle =new Bundle();
                bundle.putInt("section_number",1);
                FollowersAndFollowing.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ProfileLayout,FollowersAndFollowing,"FollowersFragment").addToBackStack("FollowersToFollowersAndFollowing").commit();
            }

        });
        TextView followingsTextView = (TextView) view.findViewById(R.id.FollowersFragment_FollowingNumber_TextView);
        followingsTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment FollowersAndFollowing = new FollowersAndFollowingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("section_number", 0);
                FollowersAndFollowing.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ProfileLayout, FollowersAndFollowing,"FollowersFragment").addToBackStack("FollowersToFollowersAndFollowing").commit();
            }
        });


        if(mUsers==null)
        {
            mNotAvaliable.setVisibility(View.VISIBLE);
        }
        else {

            recyclerView = (RecyclerView) view.findViewById(R.id.FollowersFragment_FollowersList_RecyclerView);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(layoutManager);

     //       recyclerView.setHasFixedSize(true);

            // specify an adapter
            mAdapter = new FollowersAdapter(getActivity(),mUsers);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }

    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsers = new ArrayList<>();




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
        mUsers = new ArrayList<>();

        FollowingNumber = User.length();
        Log.e("Following number",User.length()+"");
        for(int i=0;i<User.length();i++){
        try {
            String image = User.getJSONObject(i).optString("image_url");
            mUsers.add(image);
        } catch (JSONException e) {
            Log.e("User Error","ERRRRRRRORRRRRRRRRRRRRR");
        }

        }


        }



    /**
     * function to do the communication process giving url
     * @param url string url to determine the endpoint.
     *
     */
    private void HTTPRequest(String url)
    {

        DiskBasedCache cache = new DiskBasedCache(getActivity().getCacheDir(),1024*1024);
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

        JSONObject GoodReadsResponse = Response.optJSONObject("GoodreadsResponse");
        JSONArray User = GoodReadsResponse.optJSONArray("user");
        for(int i=0;i<User.length();i++)
        {
            mUsers.set(i,User.optJSONObject(i).optString("image_url"));
            Log.e("FollowersFragment",mUsers.get(i));
        }

    }

}
