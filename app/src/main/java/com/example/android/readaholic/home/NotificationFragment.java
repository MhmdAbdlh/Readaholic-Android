package com.example.android.readaholic.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.readaholic.R;

import java.util.ArrayList;

public class NotificationFragment  extends Fragment {

    public ArrayList<Notification> arrayOfNotif = new ArrayList<Notification>();
    NotificationAdapter adapter = null;
    ListView listNotif;
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

        view=inflater.inflate(R.layout.notificationlist,container,false);
        listNotif = (ListView) view.findViewById(R.id.Notificationfragment_list_listview);
        return view;
    }
}
