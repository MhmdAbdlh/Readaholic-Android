package com.example.android.readaholic;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.profile_and_profile_settings.Profile;
import com.example.android.readaholic.profile_and_profile_settings.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Hossam ahmed
 * class to handle search operation.
 */
public class SearchableActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<Users> Users = new ArrayList<Users>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        // Get the intent, verify the action and get the query
        listView = findViewById(R.id.Search_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                //execution come here when an item is clicked from
                //the search results displayed after search form is submitted
                //you can continue from here with user clicked search item
                Intent intent1 = new Intent(getBaseContext(), Profile.class);
                intent1.putExtra("user-idFromFollowingList",Users.get(position).getmUserId());
                startActivity(intent1);

                Toast.makeText(getBaseContext(),
                        "clicked search result item is "+Users.get(position).getmUserName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.e("inSearchableActivity","there  is intent");
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.e("inSearchableActivity",query);
             doMySearch(query);


        }


    }

    /**
     * to execute the search query
     * @param query parameter which is came from search view.
     */
    void doMySearch(String query )
{
    final int[] ID = new int[1];
    final String mRequestUrl = Urls.ROOT+"/api/search_by_name_username?"+"name="+query+"&token="+ UserInfo.sToken+"&type="+UserInfo.sTokenType;

    Log.e("inSearch",mRequestUrl);
    final RequestQueue mRequestQueue = Volley.newRequestQueue(this);
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject Response) {
            Log.e("inSearch",Response.toString());
            JSONArray users = Response.optJSONArray("users");
            for(int i=0;i<users.length();i++) {
                Users user = new Users();

                user.setmUserId( users.optJSONObject(i).optInt("id"));
                user.setmUserName(users.optJSONObject(i).optString("name"));
                user.setmUserImageUrl(users.optJSONObject(i).optString("image_link"));
                Users.add(user);
            }
            CustomSearchAdapter adapter = new CustomSearchAdapter(getBaseContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    Users);
            listView.setAdapter(adapter);
        mRequestQueue.stop();
            }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            //Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            // mProfileUser=null;

            mRequestQueue.stop();
        }
    });

    mRequestQueue.add(jsonObjectRequest);


}
}
