package com.example.android.readaholic.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdatesActivity extends Activity {

    final String[] jsonFile = new String[1];
    final ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
    final UpdatesAdapter adapter = new UpdatesAdapter(this, arrayOfUpadates);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);



     /* jsonFile[0] = "{\n" +
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
                "               \"name\":\"Salma Ibrahim\",\n" +
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
                "                     \"name\":\"Salma Ibrahim\",\n" +
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
                "}";*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    public void request(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.com/bins/11hzcq";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onResposeAction(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                return;
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void onResposeAction(String response){
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


                Updates updateItem = new Updates(action.getInt("type"), actor.getString("name"),updateItemJson.getString("updated_at"),updateItemJson.getInt("numLikes"),updateItemJson.getInt("numComments"),1);
                switch (updateItem.getmTypeOfUpdates()){
                    case 0:
                        JSONObject book = action.getJSONObject("book");
                        updateItem.setmBookName(book.getString("title"));
                        updateItem.setmRatingValue(action.getInt("rating"));
                        updateItem.setmAuthorName(book.getString("author"));
                        if(updateItem.getmRatingValue() == 0){
                            updateItem.setmReview(action.getString("review"));
                        }
                        break;

                    case 1:
                        JSONObject book1 = action.getJSONObject("book");
                        updateItem.setmBookName(book1.getString("title"));
                        updateItem.setmAuthorName(book1.getString("author"));
                        updateItem.setmNameofFollow(action.getString("shelf"));//shelf
                        break;

                    case 2:
                        JSONObject user = action.getJSONObject("user");
                        updateItem.setmNameofFollow(user.getString("name"));
                        break;

                    case 3: case 4:
                        JSONObject innerupdate = action.getJSONObject("update");
                        JSONObject inneraction = innerupdate.getJSONObject("action");
                        JSONObject inneractor = innerupdate.getJSONObject("actor");
                        updateItem.setmInnerUpdate(inneraction.getInt("type"));
                        updateItem.setmNameofFollow(inneractor.getString("name"));
                        updateItem.setmInnerDate(innerupdate.getString("updated_at"));

                        switch (updateItem.getmInnerUpdate()) {
                            case 0:
                                JSONObject innerbook = inneraction.getJSONObject("book");
                                updateItem.setmBookName(innerbook.getString("title"));
                                updateItem.setmRatingValue(inneraction.getInt("rating"));
                                updateItem.setmAuthorName(innerbook.getString("author"));
                                if (updateItem.getmRatingValue() == 0) {
                                    updateItem.setmReview(action.getString("review"));
                                }
                                break;
                            case 1:
                                JSONObject innerbook1 = inneraction.getJSONObject("book");
                                updateItem.setmBookName(innerbook1.getString("title"));
                                updateItem.setmAuthorName(innerbook1.getString("author"));
                                updateItem.setmShelf(inneraction.getString("shelf"));
                                break;
                            case 2:
                                JSONObject user1 = inneraction.getJSONObject("user");
                                updateItem.setmNameofFollow(user1.getString("name"));
                                break;
                        }
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

        ListView listUpadtes = (ListView) findViewById(R.id.UpadtesActivity_updateslist_listview);

        adapter.notifyDataSetChanged();
        listUpadtes.setAdapter(adapter);

    }
}