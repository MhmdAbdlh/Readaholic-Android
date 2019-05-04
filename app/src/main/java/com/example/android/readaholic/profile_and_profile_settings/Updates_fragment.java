package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import com.example.android.readaholic.home.HomeFragment;
import com.example.android.readaholic.home.Memic;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;

import java.util.ArrayList;

/**
 * Update Fragment class
 * @author Hossam ahmed
 */
public class Updates_fragment extends Fragment {
    ArrayList<Updates> arayOfUpdates = new ArrayList<Updates>();
    private ListView mListOfUpdates;
    private UpdatesAdapter adapterForUpdatesList;
    View view;
    int userId;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Users> mUsers;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            userId = bundle.getInt("user-id");
            //Toast.makeText(getContext(),String.valueOf(userId),Toast.LENGTH_SHORT).show();
        }else{
            userId = 0;
        }
        if(UserInfo.IsMemic == true) {
            HomeFragment.onResposeAction(Memic.getProfileUpdates(userId));
            showlist();
        }else {
            request();
        }
    }
    /**
     * onCreateView called when fragment is created
     * @param inflater to inflate layout
     * @param container parent of the view
     * @param savedInstanceState bundle holding the saved state
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_updates_fragment, container, false);

       /* recyclerView = (RecyclerView) view.findViewById(R.id.FollowersFragment_FollowersList_RecyclerView);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new FollowersAdapter(mUsers);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
*/

        ///////////////////////////////////////////////////////////////////////////////////////////////
        mListOfUpdates = (ListView) view.findViewById(R.id.Profile_updateslist_listview);

       /* mListOfUpdates.setOnTouchListener(new ListView.OnTouchListener() {
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
        });*/

        mListOfUpdates.setAdapter(adapterForUpdatesList);
        setListViewHeightBasedOnChildren(mListOfUpdates);
        return view;

    }

    private void request() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = Urls.ROOT+"/api/updates?user_id="+userId+"&token="+ UserInfo.sToken +"&type="+UserInfo.sTokenType;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arayOfUpdates = HomeFragment.onResposeAction(response);
                        adapterForUpdatesList = new UpdatesAdapter(getContext(),arayOfUpdates);
                        mListOfUpdates.setAdapter(adapterForUpdatesList);
                        adapterForUpdatesList.notifyDataSetChanged();
                        //Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    public void showlist(){
        adapterForUpdatesList = new UpdatesAdapter(getContext(),arayOfUpdates);
        mListOfUpdates.setAdapter(adapterForUpdatesList);
        adapterForUpdatesList.notifyDataSetChanged();
    }
}
