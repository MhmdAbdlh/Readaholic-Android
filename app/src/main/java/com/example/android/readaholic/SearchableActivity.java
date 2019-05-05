package com.example.android.readaholic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Hossam ahmed
 * class to handle search operation.
 */
public class SearchableActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<Users> Users = new ArrayList<Users>();
    SearchView SearchView;
    TextView noResult;
    TextView numOfResult;
    ProgressBar progressBar;
    String testResult="{\"users\":[{\"id\":1,\"username\":\"test\",\"name\":\"not dummy\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/1vfIqRlYgkKCFaOtsNR7N72dxINemz.jpeg\",\"gender\":\"female\",\"country\":\"Canada\",\"city\":\"Tornto\",\"followers_count\":-1,\"following_count\":-4},{\"id\":78,\"username\":\"tests\",\"name\":\"Aliko5a\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Canada\",\"city\":\"Tornto\",\"followers_count\":0,\"following_count\":0},{\"id\":1537,\"username\":\"test_1\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1538,\"username\":\"test_11\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1539,\"username\":\"test_2\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1540,\"username\":\"test_3\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1551,\"username\":\"test1\",\"name\":\"tests\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Female\",\"country\":\"Guyana\",\"city\":\"what\",\"followers_count\":0,\"following_count\":0},{\"id\":1552,\"username\":\"test12\",\"name\":\"abc\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1553,\"username\":\"test_4\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1554,\"username\":\"test_5\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0},{\"id\":1555,\"username\":\"test_6\",\"name\":\"Test\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"Male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":0,\"following_count\":0}]}";
    String salmaResult="{\"users\":[{\"id\":5,\"username\":\"Salma\",\"name\":\"Salma\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"female\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":-1,\"following_count\":0}]}";
    String waleedREsult="{\"users\":[{\"id\":3,\"username\":\"waleed\",\"name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"gender\":\"male\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"followers_count\":-1,\"following_count\":-3}]}";
    int numofResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        noResult = (TextView) findViewById(R.id.Search_NoResults_TextView);
        numOfResult = (TextView) findViewById(R.id.Search_ResultNumber_TextView);
        progressBar = (ProgressBar) findViewById(R.id.Search_ProgressBar);
        noResult.setVisibility(View.INVISIBLE);
        numOfResult.setVisibility(View.INVISIBLE);
        // Get the intent, verify the action and get the query
        listView = findViewById(R.id.Search_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //execution come here when an item is clicked from
                //the search results displayed after search form is submitted
                //you can continue from here with user clicked search item
                Intent intent1 = new Intent(getBaseContext(), Profile.class);
                intent1.putExtra("user-idFromFollowingList", Users.get(position).getmUserId());
                startActivity(intent1);

                Toast.makeText(getBaseContext(),
                        "clicked search result item is " + Users.get(position).getmUserName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        SearchView = (SearchView) findViewById(R.id.Profile_searchView);
        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("inQuerySubmit", "query text is submitted");
                doMySearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //doMySearch(newText);
                return false;
            }
        });

    }


    /**
     * to execute the search query
     *
     * @param query parameter which is came from search view.
     */
    void doMySearch(final String query) {
        final int[] ID = new int[1];
        final String mRequestUrl = Urls.ROOT + "/api/search_by_name_username?" + "name=" + query + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
        progressBar.setVisibility(View.VISIBLE);
        numOfResult.setVisibility(View.GONE);
        noResult.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        Users.clear();
        Log.e("inSearch", mRequestUrl);
 if(UserInfo.IsMemic ==false) {
     final RequestQueue mRequestQueue = Volley.newRequestQueue(this);
     final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new Response.Listener<JSONObject>() {
         @Override
         public void onResponse(JSONObject Response) {
             Log.e("inSearch", Response.toString());
             JSONArray users = Response.optJSONArray("users");
             if (users.length() > 0) {
                 for (int i = 0; i < users.length(); i++) {
                     Users user = new Users();

                     user.setmUserId(users.optJSONObject(i).optInt("id"));
                     user.setmUserName(users.optJSONObject(i).optString("name"));
                     user.setmUserImageUrl(users.optJSONObject(i).optString("image_link"));
                     Users.add(user);
                 }
                 numOfResult.setVisibility(View.VISIBLE);
                 numOfResult.setText(Integer.toString(users.length()) + " results to " + '"' + query + '"');
                 listView.setVisibility(View.VISIBLE);
                 progressBar.setVisibility(View.GONE);
                 CustomSearchAdapter adapter = new CustomSearchAdapter(getBaseContext(),
                         android.R.layout.simple_dropdown_item_1line,
                         Users);
                 listView.setAdapter(adapter);
             } else {
                 progressBar.setVisibility(View.GONE);
                 listView.setVisibility(View.INVISIBLE);
                 numOfResult.setVisibility(View.GONE);
                 noResult.setText(" 0 results to " + '"' + query + '"');
                 noResult.setVisibility(View.VISIBLE);
             }
             mRequestQueue.stop();
         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             listView.setVisibility(View.INVISIBLE);
             numOfResult.setVisibility(View.GONE);
             noResult.setText(" 0 results to " + '"' + query + '"');
             noResult.setVisibility(View.VISIBLE);
             mRequestQueue.stop();
         }
     });

     mRequestQueue.add(jsonObjectRequest);

 }
 else
     {
         JSONObject response = null;
         if(query == "salma")
         {
             try {
                 response = new JSONObject(salmaResult);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             ExtractResult(response);
             numOfResult.setVisibility(View.VISIBLE);
             numOfResult.setText(Integer.toString(numofResults) + " results to " + '"' + query + '"');
             listView.setVisibility(View.VISIBLE);
             progressBar.setVisibility(View.GONE);
             CustomSearchAdapter adapter = new CustomSearchAdapter(getBaseContext(),
                     android.R.layout.simple_dropdown_item_1line,
                     Users);
             listView.setAdapter(adapter);

         }
         else if(query == "waleed")
         {
             try {
                 response = new JSONObject(waleedREsult);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             ExtractResult(response);
             numOfResult.setVisibility(View.VISIBLE);
             numOfResult.setText(Integer.toString(numofResults) + " results to " + '"' + query + '"');
             listView.setVisibility(View.VISIBLE);
             progressBar.setVisibility(View.GONE);
             CustomSearchAdapter adapter = new CustomSearchAdapter(getBaseContext(),
                     android.R.layout.simple_dropdown_item_1line,
                     Users);
             listView.setAdapter(adapter);

         }
         else if(query == "test")
         {
             try {
                 response = new JSONObject(waleedREsult);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             ExtractResult(response);
             numOfResult.setVisibility(View.VISIBLE);
             numOfResult.setText(Integer.toString(numofResults) + " results to " + '"' + query + '"');
             listView.setVisibility(View.VISIBLE);
             progressBar.setVisibility(View.GONE);
             CustomSearchAdapter adapter = new CustomSearchAdapter(getBaseContext(),
                     android.R.layout.simple_dropdown_item_1line,
                     Users);
             listView.setAdapter(adapter);

         }
         else
             {
                 progressBar.setVisibility(View.GONE);
                 listView.setVisibility(View.INVISIBLE);
                 numOfResult.setVisibility(View.GONE);
                 noResult.setText(" 0 results to " + '"' + query + '"');
                 noResult.setVisibility(View.VISIBLE);

             }
     }

    }

    public void ExtractResult(JSONObject Response) {
        Log.e("inSearch", Response.toString());
        JSONArray users = Response.optJSONArray("users");
        if (users.length() > 0) {
            for (int i = 0; i < users.length(); i++) {
                Users user = new Users();

                user.setmUserId(users.optJSONObject(i).optInt("id"));
                user.setmUserName(users.optJSONObject(i).optString("name"));
                user.setmUserImageUrl(users.optJSONObject(i).optString("image_link"));
                Users.add(user);
            }
            numofResults = users.length();

        }
    }
}
