package com.example.android.readaholic.VolleyHelper;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.profile_and_profile_settings.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * VolleyRequest.java
 * An helper class to executes the web service using the volley. Supported
 * Methods: GET and POST.
 */
public class volleyRequestHelper {


    private Context context;
    private RequestQueue mRequestQueue;
    private JSONObject mJsonObject;
     private static Users  mProfileUser;
    private static ArrayList<Users> mUsers;
    private String profileResponse = "{\n" +
            "  \"GoodreadsResponse\": {\n" +
            "    \"Request\": {\n" +
            "      \"authentication\": \"true\",\n" +
            "      \"key\": \"FLPIgg7i8q6SY3V8asRdw\",\n" +
            "      \"method\": \"user_show \"\n" +
            "    },\n" +
            "    \"user\": {\n" +
            "      \"id\": \"93870694\",\n" +
            "      \"name\": \"Hossam Ahmed\",\n" +
            "      \"link\": \"https://www.goodreads.com/user/show/93870694-hossam-ahmed\",\n" +
            "      \"image_url\": \"https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png\",\n" +
            "      \"small_image_url\": \"https://s.gr-assets.com/assets/nophoto/user/u_50x66-632230dc9882b4352d753eedf9396530.png\",\n" +
            "      \"location\": \"Cairo, 11, Egypt\",\n" +
            "      \"joined\": \"02/2019\",\n" +
            "      \"last_active\": \"03/2019\",\n" +
            "      \"favorite_authors\": \" \",\n" +
            "      \"updates_rss_url\": \"https://www.goodreads.com/user/updates_rss/93870694?key=QENImMDqYjOB3wjlwfRE2PZJVae1YU7fcLMoS_cd4keaZ6sM\",\n" +
            "      \"reviews_rss_url\": \"https://www.goodreads.com/review/list_rss/93870694?key=QENImMDqYjOB3wjlwfRE2PZJVae1YU7fcLMoS_cd4keaZ6sM&shelf=%23ALL%23\",\n" +
            "      \"friends_count\": {\n" +
            "        \"-type\": \"integer\",\n" +
            "        \"#text\": \"0\"\n" +
            "      },\n" +
            "      \"groups_count\": \"0\",\n" +
            "      \"reviews_count\": {\n" +
            "        \"-type\": \"integer\",\n" +
            "        \"#text\": \"7\"\n" +
            "      },\n" +
            "      \"user_shelves\": {\n" +
            "        \"-type\": \"array\",\n" +
            "        \"user_shelf\": [\n" +
            "          {\n" +
            "            \"id\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"305076705\"\n" +
            "            },\n" +
            "            \"name\": \"read\",\n" +
            "            \"book_count\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"2\"\n" +
            "            },\n" +
            "            \"exclusive_flag\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"description\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"sort\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"order\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"per_page\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"featured\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"recommend_for\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"sticky\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"305076703\"\n" +
            "            },\n" +
            "            \"name\": \"currently-reading\",\n" +
            "            \"book_count\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"2\"\n" +
            "            },\n" +
            "            \"exclusive_flag\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"description\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"sort\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"order\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"per_page\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"featured\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"recommend_for\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"sticky\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"305076701\"\n" +
            "            },\n" +
            "            \"name\": \"to-read\",\n" +
            "            \"book_count\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"#text\": \"3\"\n" +
            "            },\n" +
            "            \"exclusive_flag\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"description\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"sort\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"order\": {\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"per_page\": {\n" +
            "              \"-type\": \"integer\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            },\n" +
            "            \"featured\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"false\"\n" +
            "            },\n" +
            "            \"recommend_for\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"#text\": \"true\"\n" +
            "            },\n" +
            "            \"sticky\": {\n" +
            "              \"-type\": \"boolean\",\n" +
            "              \"-nil\": \"true\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"updates\": {\n" +
            "        \"-type\": \"array\",\n" +
            "        \"#text\": \"...\"\n" +
            "      },\n" +
            "      \"user_statuses\": \" \"\n" +
            "    }\n" +
            "  }\n" +
            "}";
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
     * Constructor
     *
     * @param context hold the context of the view .
     */
    public volleyRequestHelper(Context context) {
        this.context = context;
        mUsers = new ArrayList<>();
        DiskBasedCache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
    }

    public volleyRequestHelper() {
    }

    /**
     * Get user holding data extracted from the json response.
     *
     * @return User obj
     */
    public Users getmUser(String Url) {
        mProfileUser = new Users();
        DiskBasedCache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject Response) {
                Log.e("profileResponse",Response.toString());
                mProfileUser.setmUserName(Response.optString("name"));
                Log.e("Test" ,mProfileUser.getmUserName());
                mProfileUser.setmUserImageUrl(Response.optString("small_image_link"));
                mProfileUser.setmUsernumberOfBooks(Response.optInt("books-count"));

                Log.e("showprofileResponseName",mUsers.get(0).getmUserName());
                mRequestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                mUsers.set(0,null);
                mRequestQueue.stop();
            }
        });
        mRequestQueue.add(jsonObjectRequest);

        return mProfileUser;
    }

    /**
     * Request Json response from the Web API.
     *
     * @param requestName   the String refers the request name
     * @param webserviceUrl the String refers the web service URL.
     * @param requestParams the list of parameters in byte array.
     * @param webMethod     the integer indicates the web method.
     * @param getCache      the boolean indicates whether cache can enable/disable
     * @return user after request
     */
    public ArrayList<Users> JsonObjectRequest(final String requestName,
                                  final String webserviceUrl,
                                  final byte[] requestParams, final int webMethod,
                                  final boolean getCache) {


        if(requestName == "ShowProfile") {
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(webMethod, webserviceUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("profileResponse",response.toString());
                    ExtractUser(response);
                    Log.e("showprofileResponseName",mUsers.get(0).getmUserName());
                    mRequestQueue.stop();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                   mUsers.set(0,null);
                   mRequestQueue.stop();
                }
            });
            mRequestQueue.add(jsonObjectRequest);
            return mUsers;
        }
        else if(requestName == "Followinglist")
        {
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(webMethod, webserviceUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("JsonResponse",response.toString());
                    mUsers = ExtractFollowingArray(response);
                    mRequestQueue.stop();
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mUsers = null;
                    mRequestQueue.stop();
                }
            });
            mRequestQueue.add(jsonObjectRequest);

        }
        else if(requestName == "Followerslist")
        {

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(webMethod, webserviceUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("JsonFollowersResponse",response.toString());
                    mUsers = ExtractFollowersArray(response);
                    mRequestQueue.stop();
                }
            }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mUsers = null;
                    mRequestQueue.stop();
                }
            });
            mRequestQueue.add(jsonObjectRequest);

        }
        return mUsers;
    }


    private void ExtractUser(JSONObject Response)
     {
         Users user = new Users();
         user.setmUserName(Response.optString("name"));
         Log.e("Test" ,user.getmUserName());
         user.setmUserImageUrl(Response.optString("small_image_link"));
         user.setmUsernumberOfBooks(Response.optInt("books-count"));
         mUsers.add(user);
     }


    /**
     * extract the followings
     * @param Response json object of response
     * @return array list of users
     */
    private ArrayList<Users> ExtractFollowingArray(JSONObject Response)
    {
        mUsers = new ArrayList<>();
        Users user = new Users();
        JSONArray Following = Response.optJSONArray("following");
        if(Following.length() ==0)
        {
            mUsers = null;
        }
        else
            {
        for (int i=0;i<Following.length();i++) {
            user.setmUserName(Following.optJSONObject(i).optString("name"));
            user.setmUserId(Following.optJSONObject(i).optInt("id"));
            user.setmUserImageUrl(Following.optJSONObject(i).optString("image_link"));
            mUsers.add(user);
        }
        }
        return mUsers;
    }

    /**
     * extract the followers
     * @param Response json object of response
     * @return array list of users
     */
    private ArrayList<Users> ExtractFollowersArray(JSONObject Response)
    {
        mUsers = new ArrayList<>();
        Users user = new Users();
        JSONArray Followers = Response.optJSONArray("followers");
        if(Followers.length() ==0)
        {
            mUsers = null;
        }
        else
            {
        for (int i=0;i<Followers.length();i++) {
            user.setmUserName(Followers.optJSONObject(i).optString("name"));
            user.setmUserId(Followers.optJSONObject(i).optInt("id"));
            user.setmUserImageUrl(Followers.optJSONObject(i).optString("image_link"));
            mUsers.add(user);
        }
        }
        return mUsers;
    }
}


