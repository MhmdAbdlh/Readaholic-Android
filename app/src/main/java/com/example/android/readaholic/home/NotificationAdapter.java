package com.example.android.readaholic.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.readaholic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    private Context activity;
    private Notification Item;
    public NotificationAdapter(Context context, ArrayList<Notification> objects) {
        super(context, 0, objects);
        activity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item = getItem(position);
        View ListItemView = convertView;
        if (ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.notification_item, parent, false);
        }
        ImageView userimg = ListItemView.findViewById(R.id.Notification_profile_image_Imageview);
        TextView type = ListItemView.findViewById(R.id.NotificationFragment_typeofnotifcation_textview);
        TextView date = ListItemView.findViewById(R.id.NotificationFragment_date_textview);

        Picasso.get().load(Item.getmImageUrl()).into(userimg);
        date.setText(Item.getmDate());
        String name;
        if (Item.getmUserID() == 0) {
            name = "you";
        }else{
            name = Item.getmUserName();
        }
        switch (Item.getmType()) {
            case 2:
                type.setText(Item.getmName()+" is now following you");
                break;
            case 1:
                type.setText(Item.getmName()+" liked "+name+" review of "+Item.getmBookName());
                break;
            case 0:
                type.setText(Item.getmName()+" commented on "+name+" review of "+Item.getmBookName());
                break;
        }
        if(Item.getmreadornot() == 0){
            ListItemView.setBackgroundResource(R.color.colorPrimary);
        }
        else{
            ListItemView.setBackgroundResource(R.color.colorWhite);
        }

        return ListItemView;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}
