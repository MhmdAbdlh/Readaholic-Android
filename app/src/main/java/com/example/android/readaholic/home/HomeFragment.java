package com.example.android.readaholic.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.example.android.readaholic.profile_and_profile_settings.Followers_fragment;
import com.example.android.readaholic.profile_and_profile_settings.ProfileFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public ArrayList<Updates> arrayOfUpadates1 = new ArrayList<Updates>();
    UpdatesAdapter adapter = null;
    ProgressBar loading ;
    ListView listUpadtes;
    View view;
    public static String newjson ="{\n" +
            "    \"status\": \"true\",\n" +
            "    \"updates\": [\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"body\": null,\n" +
            "            \"rating\": 5,\n" +
            "            \"likes_count\":66,\n" +
            "            \"comments_count\": 77,\n" +
            "            \"updated_at\": \"2019-03-21 00:00:00\",\n" +
            "            \"book_id\": 1,\n" +
            "            \"title\": \"the great book\",\n" +
            "            \"description\": \"\",\n" +
            "            \"img_url\": \"asfafaaf\",\n" +
            "            \"reviews_count\": 11,\n" +
            "            \"ratings_count\": 44,\n" +
            "            \"ratings_avg\": 44,\n" +
            "            \"pages_no\": 44,\n" +
            "            \"user_id\": 2,\n" +
            "            \"name\": \"sam\",\n" +
            "            \"image_link\": fff,\n" +
            "            \"author_name\": \"Dean Winchester\",\n" +
            "            \"update_type\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"shelf_type\": 3,\n" +
            "            \"updated_at\": \"2019-03-15 00:00:00\",\n" +
            "            \"likes_count\":66,\n" +
            "            \"comments_count\": 6,\n" +
            "            \"book_id\": 1,\n" +
            "            \"title\": \"a\",\n" +
            "            \"description\": \"\",\n" +
            "            \"img_url\": \"yyy\",\n" +
            "            \"reviews_count\": 6,\n" +
            "            \"ratings_count\": 7,\n" +
            "            \"ratings_avg\": 8,\n" +
            "            \"pages_no\": null,\n" +
            "            \"user_id\": 2,\n" +
            "            \"name\": \"Tom\",\n" +
            "            \"image_link\": 99,\n" +
            "            \"author_name\": \"ghk\",\n" +
            "            \"update_type\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"shelf_type\": 3,\n" +
            "            \"updated_at\": \"2019-03-01 00:00:00\",\n" +
            "            \"likes_count\": null,\n" +
            "            \"comments_count\": null,\n" +
            "            \"book_id\": 1,\n" +
            "            \"title\": \"a\",\n" +
            "            \"description\": \"\",\n" +
            "            \"img_url\": \"\",\n" +
            "            \"reviews_count\": null,\n" +
            "            \"ratings_count\": null,\n" +
            "            \"ratings_avg\": null,\n" +
            "            \"pages_no\": null,\n" +
            "            \"user_id\": 3,\n" +
            "            \"name\": \"\",\n" +
            "            \"image_link\": null,\n" +
            "            \"author_name\": \"a\",\n" +
            "            \"update_type\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"updated_at\": \"2019-03-19 00:00:00\",\n" +
            "            \"u_id\": 2,\n" +
            "            \"user_image_link\": null,\n" +
            "            \"user_name\": \"\",\n" +
            "            \"followed_id\": 3,\n" +
            "            \"followed_image_link\": null,\n" +
            "            \"followed_name\": \"\",\n" +
            "            \"update_type\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"resourse_type\": 0,\n" +
            "            \"updated_at\": null,\n" +
            "            \"comment_body\": \"\",\n" +
            "            \"review_id\": 1,\n" +
            "            \"body\": null,\n" +
            "            \"rating\": null,\n" +
            "            \"comments_count\": null,\n" +
            "            \"review_updated_at\": \"2019-03-03 00:00:00\",\n" +
            "            \"book_id\": 1,\n" +
            "            \"title\": \"a\",\n" +
            "            \"description\": \"\",\n" +
            "            \"img_url\": \"\",\n" +
            "            \"reviews_count\": null,\n" +
            "            \"ratings_count\": null,\n" +
            "            \"ratings_avg\": null,\n" +
            "            \"pages_no\": null,\n" +
            "            \"user_id\": 1,\n" +
            "            \"name\": \"\",\n" +
            "            \"image_link\": null,\n" +
            "            \"author_name\": \"a\",\n" +
            "            \"update_type\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"resourse_type\": 0,\n" +
            "            \"updated_at\": null,\n" +
            "            \"review_id\": 1,\n" +
            "            \"body\": null,\n" +
            "            \"rating\": null,\n" +
            "            \"likes_count\": null,\n" +
            "            \"comments_count\": null,\n" +
            "            \"review_updated_at\": \"2019-03-03 00:00:00\",\n" +
            "            \"book_id\": 1,\n" +
            "            \"title\": \"a\",\n" +
            "            \"description\": \"\",\n" +
            "            \"img_url\": \"\",\n" +
            "            \"reviews_count\": null,\n" +
            "            \"ratings_count\": null,\n" +
            "            \"ratings_avg\": null,\n" +
            "            \"pages_no\": null,\n" +
            "            \"user_id\": 1,\n" +
            "            \"name\": \"\",\n" +
            "            \"image_link\": null,\n" +
            "            \"author_name\": \"a\",\n" +
            "            \"update_type\": 3\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public static String jsonFile = "{\n" +
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
            "                     \"name\":\"Salma \",\n" +
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

    TabItem mUpdtesfragment ;
    TabItem mNotificationfragment;
    @Nullable
    @Override
    /**
     * Called when the activity is first created.
     * Creating in it ListView contians the array of Updates for the currently User
     *
     * @param inflater LayoutInflater:The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container  ViewGroup:the parent view that the fragment's UI should be attached to
     * @param savedInstanceState  Bundle:this fragment is being re-constructed from a previous saved state as given here.
     *
     * @return 	Return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_updates,container,false);
       // adapter = new MaterialAdapter(getContext(),MaterialList);
       // ListView list = (ListView) myview.findViewById(R.id.MaterialstList);
       // list.setAdapter(adapter);
        loading = (ProgressBar) view.findViewById(R.id.UpdatesActivity_loading_progbar);
        adapter = new UpdatesAdapter(getContext(), arrayOfUpadates1);

        listUpadtes = (ListView) view.findViewById(R.id.UpadtesActivity_updateslist_listview);
        listUpadtes.setEmptyView(view.findViewById(R.id.empty));
        listUpadtes.setAdapter(adapter);

        listUpadtes.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);




        return view;
    }



    @Override
    /**
     * Called when the activity is first created.
     * Calling in it the funcion the create the array of updates to give it to adapter.
     *
    * @param savedInstanceState  Bundle:this fragment is being re-constructed from a previous saved state as given here.
     *
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //arrayOfUpadates1 = onResposeAction(newjson);
        //arrayOfUpadates1 = onResposeAction1(jsonFile);
        request();
       // Toast.makeText(getContext(),"salma",Toast.LENGTH_SHORT).show();

    }

    /**
     * request the json file of updates list to be displayed.
     * in the response we call the function that create the array of updates
     *
     */
    public void request(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = Urls.ROOT+"/api/updates?token="+ UserInfo.sToken +"&type="+UserInfo.sTokenType;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayOfUpadates1 = onResposeAction(response);
                        adapter = new UpdatesAdapter(getContext(), arrayOfUpadates1);
                        listUpadtes.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                        listUpadtes.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    /**
     * Create array of updates of different types.
     * @param response the json string that contains array of updates
     * @return Arraylist contains updates of the user that was extract from json.
     */
    static public ArrayList<Updates> onResposeAction(String response){
        ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
        JSONObject root = null;
        String name = "hh";
        JSONArray updatesArray  = null;

        try {
            updatesArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < updatesArray.length(); i++) {
            try {

                JSONObject updateItemJson = updatesArray.getJSONObject(i);

                Updates updateItem = new Updates(updateItemJson.getInt("update_type"), updateItemJson.getString("name"),updateItemJson.getString("updated_at"),9,10,updateItemJson.getInt("user_id"));
                if(updateItemJson.getString("image_link") == null){updateItem.setmUserimg("0");}
                updateItem.setmUserimg(updateItemJson.getString("image_link"));
                switch (updateItem.getmTypeOfUpdates()){
                    //review or raring update
                    case 0:
                        updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                        updateItem.setmReviewID(updateItemJson.getInt("review_id"));
                        updateItem.setmBookCover(updateItemJson.getString("img_url"));
                        updateItem.setmBookName(updateItemJson.getString("title"));
                        updateItem.setmRatingValue(updateItemJson.getInt("rating"));
                        updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                        updateItem.setmBookId(updateItemJson.getInt("book_id"));
                        if(updateItem.getmRatingValue() == 0){
                            //if type of only revies Disable rating value and assign review
                            updateItem.setmReview(updateItemJson.getString("body"));
                        }
                        break;
                     //shelves
                    case 1:
                        updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                        updateItem.setmBookCover(updateItemJson.getString("img_url"));
                        updateItem.setmBookName(updateItemJson.getString("title"));
                        updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                        updateItem.setmBookId(updateItemJson.getInt("book_id"));
                        updateItem.setmShelfUpdateType(updateItemJson.getInt("shelf_type"));//shelf
                        break;
                    //follwing
                    case 2:
                        updateItem.setmNameofFollow(updateItemJson.getString("followed_name"));
                        updateItem.setmInnerImgUrl(updateItemJson.getString("followed_image_link"));
                        updateItem.setmInnerUserId(updateItemJson.getInt("followed_id"));
                        break;
                    //liked or commented on post
                    case 3: case 4:
                        updateItem.setmInnerUserId(updateItemJson.getInt("rev_user_id"));
                        updateItem.setmInnerUpdate(0);//always in reviews
                        updateItem.setmNameofFollow(updateItemJson.getString("rev_user_name"));
                        updateItem.setmInnerDate(updateItemJson.getString("review_updated_at"));
                        //type of the inner post
                        switch (updateItem.getmInnerUpdate()) {
                            //review or rating
                            case 0:
                                updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                                updateItem.setmReviewID(updateItemJson.getInt("review_id"));
                                updateItem.setmBookCover(updateItemJson.getString("img_url"));
                                updateItem.setmBookName(updateItemJson.getString("title"));
                                updateItem.setmRatingValue(updateItemJson.getInt("rating"));
                                updateItem.setmInnerImgUrl(updateItemJson.getString("rev_user_imageLink"));
                                updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                                updateItem.setmBookId(updateItemJson.getInt("book_id"));
                                if (updateItem.getmRatingValue() == 0) {
                                    updateItem.setmReview(updateItemJson.getString("body"));
                                }
                                break;
                            //shelves
                            case 1:
                                updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                                updateItem.setmBookCover(updateItemJson.getString("imgUrl"));
                                updateItem.setmBookName(updateItemJson.getString("title"));
                                updateItem.setmBookId(updateItemJson.getInt("book_id"));
                                updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                                updateItem.setmShelf(updateItemJson.getString("shelf"));
                                break;
                            //follwing
                            case 2:
                                JSONObject user1 = updateItemJson.getJSONObject("user");
                                updateItem.setmNameofFollow(user1.getString("name"));
                                break;
                        }
                        //commented on post assign comment to show it
                        if(updateItem.getmTypeOfUpdates() == 4){
                            updateItem.setmComment(updateItemJson.getString("comment_body"));
                        }
                        break;
                }
                arrayOfUpadates.add(updateItem);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayOfUpadates;
    }
    static public ArrayList<Updates> onResposeAction1(String response){
        ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
        JSONObject root = null;
        String name = "hh";
        JSONArray updatesArray  = null;

        try {
            root = new JSONObject(response);
            JSONObject update = root.getJSONObject("updates");
            updatesArray = update.getJSONArray("update");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < updatesArray.length(); i++) {
            try {

                JSONObject updateItemJson = updatesArray.getJSONObject(i);
                JSONObject actor = updateItemJson.getJSONObject("actor");
                JSONObject action = updateItemJson.getJSONObject("action");


                Updates updateItem = new Updates(action.getInt("type"), actor.getString("name"),updateItemJson.getString("updated_at"),updateItemJson.getInt("numLikes"),updateItemJson.getInt("numComments"),actor.getInt("id"));
                switch (updateItem.getmTypeOfUpdates()){
                    //review or raring update
                    case 0:
                        JSONObject book = action.getJSONObject("book");
                        updateItem.setmBookCover(book.getString("imgUrl"));
                        updateItem.setmBookName(book.getString("title"));
                        updateItem.setmRatingValue(action.getInt("rating"));
                        updateItem.setmAuthorName(book.getString("author"));
                        if(updateItem.getmRatingValue() == 0){
                            //if type of only revies Disable rating value and assign review
                            updateItem.setmReview(action.getString("review"));
                        }
                        break;
                    //shelves
                    case 1:
                        JSONObject book1 = action.getJSONObject("book");
                        updateItem.setmBookCover(book1.getString("imgUrl"));
                        updateItem.setmBookName(book1.getString("title"));
                        updateItem.setmAuthorName(book1.getString("author"));
                        updateItem.setmShelfUpdateType(action.getInt("shelf_type"));//shelf
                        break;
                    //follwing
                    case 2:
                        JSONObject user = action.getJSONObject("user");
                        updateItem.setmNameofFollow(user.getString("name"));
                        break;
                    //liked or commented on post
                    case 3: case 4:
                        JSONObject innerupdate = action.getJSONObject("update");
                        JSONObject inneraction = innerupdate.getJSONObject("action");
                        JSONObject inneractor = innerupdate.getJSONObject("actor");
                        updateItem.setmInnerUserId(inneractor.getInt("id"));
                        updateItem.setmInnerUpdate(inneraction.getInt("type"));
                        updateItem.setmNameofFollow(inneractor.getString("name"));
                        updateItem.setmInnerDate(innerupdate.getString("updated_at"));
                        //type of the inner post
                        switch (updateItem.getmInnerUpdate()) {
                            //review or rating
                            case 0:
                                JSONObject innerbook = inneraction.getJSONObject("book");
                                updateItem.setmBookCover(innerbook.getString("imgUrl"));
                                updateItem.setmBookName(innerbook.getString("title"));
                                updateItem.setmRatingValue(inneraction.getInt("rating"));
                                updateItem.setmAuthorName(innerbook.getString("author"));
                                if (updateItem.getmRatingValue() == 0) {
                                    updateItem.setmReview(action.getString("review"));
                                }
                                break;
                            //shelves
                            case 1:
                                JSONObject innerbook1 = inneraction.getJSONObject("book");
                                updateItem.setmBookCover(innerbook1.getString("imgUrl"));
                                updateItem.setmBookName(innerbook1.getString("title"));
                                updateItem.setmAuthorName(innerbook1.getString("author"));
                                updateItem.setmShelf(inneraction.getString("shelf"));
                                break;
                            //follwing
                            case 2:
                                JSONObject user1 = inneraction.getJSONObject("user");
                                updateItem.setmNameofFollow(user1.getString("name"));
                                break;
                        }
                        //commented on post assign comment to show it
                        if(updateItem.getmTypeOfUpdates() == 4){
                            updateItem.setmComment(action.getString("comment"));
                        }
                        break;
                }
                arrayOfUpadates.add(updateItem);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayOfUpadates;
    }
    @Override
    public void onResume() {
        super.onResume();
        request();
    }


}

