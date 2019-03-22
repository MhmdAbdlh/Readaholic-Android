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


/**
 * class FollowersTab Fragment of the tabbed fragment
 * @author Hossam ahmed
 */
public class FollowersTab_Fragment extends Fragment {
     List<Users> followers;
     TextView mNotAvaliableTextView;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManger;
     FollowersListAdapter mAdapter;

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
        View view = inflater.inflate(R.layout.followerstab_fragment,container,false);
        mNotAvaliableTextView = (TextView) view.findViewById(R.id.FollowersTab_fragment_NotAvaliable_TextView);
        mNotAvaliableTextView.setVisibility(View.INVISIBLE);


        if(followers==null)
        {
            mNotAvaliableTextView.setVisibility(View.VISIBLE);
        }
        else {

            mRecyclerView = (RecyclerView) view.findViewById(R.id.FollowersTab_fragment_FollowersList_RecyclerView);

            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManger = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView.setLayoutManager(mLayoutManger);

            // specify an adapter
            mAdapter = new FollowersListAdapter(getContext(),followers);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }

    /**
     *onCreate  called when fragment is created to get the data before view is created
     * @param savedInstanceState bundle of saved states
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        followers = new ArrayList<>();
        followers.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        followers.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        followers.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        followers.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        followers.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));
        followers.add(new Users("hossam",
                5,5,"https://images.gr-assets.com/users/1507144891p3/7004371.jpg",7,null,null,null));

    }
}
