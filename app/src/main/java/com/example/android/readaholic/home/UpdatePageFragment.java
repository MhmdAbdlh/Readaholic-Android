package com.example.android.readaholic.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdatePageFragment extends Fragment {

    public ArrayList<Updates> arrayOfUpadates1 = new ArrayList<Updates>();
    UpdatesAdapter adapter = null;
    ListView listUpadtes;
    View view;
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
     * @return 	Return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_update_page,container,false);
        final ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
        Bundle bundle = this.getArguments();
        Updates Item = null;
        if (bundle != null) {
           Item = bundle.getParcelable("UpdateItem");
        }
        LinearLayout likedPostView = UpdatesAdapter.likedpost;
        TextView commentView = UpdatesAdapter.commentView;
        Item.setmNewActivity(1);
        arrayOfUpadates.add(Item);

        ListView listUpadtes = (ListView) view.findViewById(R.id.UpdatePage_listview);
        UpdatesAdapter adapter = new UpdatesAdapter(getContext(), arrayOfUpadates);

        listUpadtes.setAdapter(adapter);

        return view;
    }
}