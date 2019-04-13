package com.example.android.readaholic.profile_and_profile_settings;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
 * class FollowingTab Fragment of the tabbed fragment
 * @author Hossam ahmed
 */
public class Followingtab_Fragment extends Fragment {
     List<Users> mUser;
     TextView mNotAvaliableTextView;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManger;
     FollowingLiastAdapter mAdapter;
    RequestQueue mRequestQueue;
    TextView Title;
    View view;
    int userId;
    int followingNum;
    private static ProgressDialog mProgressDialog;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.followingtab_fragment,container,false);
        Title = (TextView)view.findViewById(R.id.Followingtab_fragment_Followings_TextView);

        mNotAvaliableTextView = (TextView) view.findViewById(R.id.Followingtab_fragment_NotAvaliableTextView);
        mNotAvaliableTextView.setVisibility(View.INVISIBLE);

        //TextView FollowingNumber = (TextView)view.findViewById(R.id.Followingtab_fragment_Followings_TextView);
        //FollowingNumber.setText(FollowingNumber+" Following");

        Bundle bundle = getArguments();
        if(bundle == null)
            userId =0;
        else {
            userId = bundle.getInt("user-id");
            followingNum = bundle.getInt("following-num");
        }
        ExtractFollowingsArray(userId);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * function to extract followings array of users from response
     */
    private void ExtractFollowingsArray(int id)
    {

        mUser = new ArrayList<>();
        DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
        BasicNetwork network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String mRequestUrl;
        if(id == 0)
        mRequestUrl = Urls.ROOT  + "/api/following?"+"token="+
                UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        else
            mRequestUrl = Urls.ROOT  + "/api/following?id="+Integer.toString(id)+"&token="+
                    UserInfo.sToken+"&type="+ UserInfo.sTokenType;

        //showSimpleProgressDialog(getContext(),"Loading.....","Loading Followings",false);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject Response) {
                        Log.e("following tab response",Response.toString());

                        JSONArray followings = Response.optJSONArray("following");
                        if (followings == null) {
                            Log.e("Following tab test","following tab has null following array");
                            mUser = null;
                        } else {
                            for (int i = 0; i < followings.length(); i++) {
                                Users user = new Users();
                                user.setmUserName(followings.optJSONObject(i).optString("name"));
                                user.setmUserId(followings.optJSONObject(i).optInt("id"));
                                user.setmUserImageUrl(followings.optJSONObject(i).optString("image_link"));
                                mUser.add(user);
                            }
                            Log.e("following Test",mUser.get(1).getmUserName());
                            UpdateList();
                        }

                        mRequestQueue.stop();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mUser = null;
                mRequestQueue.stop();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }
    private void UpdateList()
    {
        //removeSimpleProgressDialog();
        if(mUser.isEmpty())
        {
            mNotAvaliableTextView.setVisibility(View.VISIBLE);
        }
        else {

            Title.setText("FOLLOWING "+Integer.toString(followingNum)+" READERS");
            mRecyclerView = (RecyclerView) view.findViewById(R.id.Followingtab_fragment_FollowingsList_RecyclerView);

            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView.setLayoutManager(mLayoutManger);


            // specify an adapter
            mAdapter = new FollowingLiastAdapter(getContext(), mUser, new FollowingLiastAdapter.customItemCLickLisenter() {
                @Override
                public void onItemClick(View v, int position) {
                   int userId = mUser.get(position).getmUserId();
                    Intent profileIntent = new Intent(getContext(), Profile.class);
                    profileIntent.putExtra("user-idFromFollowingList",userId);
                    Log.e("Followng_tab","following tab user id"+Integer.toString(userId));
                    startActivity(profileIntent);
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        }

    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
