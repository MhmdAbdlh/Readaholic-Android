package com.example.android.readaholic;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.readaholic.profile_and_profile_settings.Users;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomSearchAdapter extends ArrayAdapter {

    private List<Users> dataList;
    private Context mContext;
    private int searchResultItemLayout;

    public CustomSearchAdapter(Context context, int resource, List<Users> storeSourceDataLst) {
        super(context, resource, storeSourceDataLst);
        dataList = storeSourceDataLst;
        mContext = context;
        searchResultItemLayout = resource;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Users getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.following_list, parent, false);
        }
        TextView userNameTextView = (TextView) view.findViewById(R.id.FollowingList_UserIName_TextView);
        ImageView userImageView =(ImageView) view.findViewById(R.id.FollowingList_UserImage_ImageView);

        Picasso.get().load(dataList.get(position).getmUserImageUrl()).transform(new CircleTransform()).into(userImageView);
        userNameTextView.setText( dataList.get(position).getmUserName());

        return view;
    }
}