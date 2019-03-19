package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.readaholic.R;

import java.util.ArrayList;


public class Updates_fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Users> mUsers;

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
        return view;

    }

}
