package com.example.android.readaholic.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<Notification> {
private Context activity;

public NotificationAdapter(Context context, ArrayList<Notification> objects) {
    super(context, 0, objects);
    activity = context;
}
}
