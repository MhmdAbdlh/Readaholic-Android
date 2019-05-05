package com.example.android.readaholic.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabWidget;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.books.Creviewdata;
import com.example.android.readaholic.books.ReviewActivity;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.profile_and_profile_settings.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@VisibleForTesting
public class NotificationFragment  extends Fragment {

    private ArrayList<Notification> arrayOfNotif = new ArrayList<Notification>();
    private NotificationAdapter adapter;
    private ListView listNotif;
    static private TabWidget tabs;
    private View view;
    String jsonfile = "[\n" +
            "            {\n" +
            "                \"id\": \"1\",\n" +
            "                \"actors\": {\n" +
            "                    \"user\": {\n" +
            "                        \"id\": \"000000\",\n" +
            "                        \"name\": \"Salma\",\n" +
            "                        \"link\": \"https:\\/\\/www.goodreads.com\\/user\\/show\\/000000-salma\\n\",\n" +
            "                        \"image_url\": \"\\nhttps:\\/\\/images.jpg\\n\",\n" +
            "                        \"has_image\": \"true\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"new\": \"true\",\n" +
            "                \"created_at\": \"2019-03-08T04:15:46-08:00\",\n" +
            "                \"url\": \"https:\\/\\/www.goodreads.com\\/comment\\/show\\/1111111\",\n" +
            "                \"resource_type\": \"Comment\",\n" +
            "                \"group_resource_type\": \"ReadStatus\"\n" +
            "            }\n" +
            "        ]";

    @Nullable


    @Override
    /**
     *Called when the activity is first created.
     * Creating in it ListView contians only one item of clicked update that was pass from another fragment.
     *
     * @param inflater LayoutInflater:The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container  ViewGroup:the parent view that the fragment's UI should be attached to
     * @param savedInstanceState  Bundle:this fragment is being re-constructed from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.notificationlist, container, false);

        listNotif = (ListView) view.findViewById(R.id.Notificationfragment_list_listview);

        listNotif.setEmptyView(view.findViewById(R.id.notiempty));
        //listNotif.setBackgroundResource(R.drawable.customshape);
        listNotif.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notification selectedItem = (Notification) parent.getItemAtPosition(position);
                if(selectedItem.getmreadornot() == 0){
                    if(UserInfo.IsMemic != true) {
                        requestmark(selectedItem.getMid());
                        selectedItem.setMreadornot(1);
                        listNotif.setAdapter(adapter);
                    }else{

                        selectedItem.setMreadornot(1);
                        listNotif.setAdapter(adapter);
                    }
                }
                if (selectedItem.getmType() == 2) {

                    Intent profileIntent = new Intent(view.getContext(), Profile.class);
                    profileIntent.putExtra("user-idFromFollowingList", selectedItem.getMuserid());
                    view.getContext().startActivity(profileIntent);
                } else {

                    Intent i = new Intent(getContext(), ReviewActivity.class);
                    Creviewdata.INSTANCE.setReviewid(selectedItem.getmReviewId());
                    getContext().startActivity(i);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Request();
        if(UserInfo.IsMemic == false){
         //onResposeAction(Memic.notifiupdatesid1);

        /*    listNotif = (ListView) view.findViewById(R.id.Notificationfragment_list_listview);
            adapter = new NotificationAdapter(getContext(), arrayOfNotif);
            listNotif.setAdapter(adapter);
            adapter.notifyDataSetChanged();        }else {
            Request();
        */}
    }

    public void onResume(){

        super.onResume();
        listNotif.setAdapter(adapter);
    }
    /**
     * Create array of Notification of different types.
     * @param response the json string that contains array of Notification
     * @return Arraylist contains Notification of the user that was extract from json.
     */
    public ArrayList<Notification> onResposeAction(String response) {
        JSONObject root = null;
        int count = 0;
        String name = "hh";
        JSONArray notiArray = null;

        try {
            notiArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < notiArray.length(); i++) {
            try {

                JSONObject notifiItemJson = notiArray.getJSONObject(i);
                JSONObject data = notifiItemJson.getJSONObject("data");
                Notification notifItem = new Notification(notifiItemJson.getInt("read"),notifiItemJson.getInt("n_id"), data.getInt("type"), data.getString("user_image_link"), data.getString("user_name"), data.getInt("user_id"));
                if(notifItem.getmType() == 0 || notifItem.getmType() == 1) {
                    notifItem.setmReviewId(data.getInt("review_id"));
                    notifItem.setmBookName(data.getString("book_title"));
                    notifItem.setmUserName(data.getString("review_user_name"));
                    notifItem.setmUserID(data.getInt("review_user_id"));
                    if(notifiItemJson.getInt("read") == 0){
                        count++;
                    }
                }
                arrayOfNotif.add(notifItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if(count != 0){
        }
        return arrayOfNotif;
    }
    /**
     * request the json file of Notification list to be displayed.
     * in the response we call the function that create the array of Notification
     *
     */
    public void Request(){

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = Urls.ROOT+"/api/notification?token="+ UserInfo.sToken +"&type="+UserInfo.sTokenType;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayOfNotif = onResposeAction(response);
                        adapter = new NotificationAdapter(getContext(), arrayOfNotif);
                        listNotif.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
    /**
     * Mark notification as it was read by clicking on it
     * @param id the id of Notification that was clocked on it
     */
    public void requestmark(final int id){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Urls.ROOT+"/api/mark_notification", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getContext(),"ssalma",Toast.LENGTH_LONG);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG);
            }
        }) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",String.valueOf(id));
                params.put("token", UserInfo.sToken);
                params.put("type", UserInfo.sTokenType);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(request);
    }

    /**
     * to fill listview with arrayof Notification after request was success
     */
    public void showlist(){

    }
}