package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.readaholic.R;

import java.util.ArrayList;
import java.util.List;

public class Followingtab_Fragment extends Fragment {
     List<Users> mUser;
     TextView mNotAvaliableTextView;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManger;
     FollowingLiastAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.followingtab_fragment,container,false);

        mNotAvaliableTextView = (TextView) view.findViewById(R.id.Followingtab_fragment_NotAvaliableTextView);
        mNotAvaliableTextView.setVisibility(View.INVISIBLE);
        if(mUser==null)
        {
            mNotAvaliableTextView.setVisibility(View.VISIBLE);
        }
        else {

            mRecyclerView = (RecyclerView) view.findViewById(R.id.Followingtab_fragment_FollowingsList_RecyclerView);

            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView.setLayoutManager(mLayoutManger);


            // specify an adapter
            mAdapter = new FollowingLiastAdapter(getContext(),mUser);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         mUser= new ArrayList<>();
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        mUser.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));

    }
}
