package com.example.android.readaholic.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.readaholic.R;

import java.util.ArrayList;

public class UpdatePage extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);


        final ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
        Intent intent = getIntent();
        LinearLayout likedPostView = UpdatesAdapter.likedpost;
        TextView commentView = UpdatesAdapter.commentView;
        Updates Item = intent.getParcelableExtra("UpdateItem");
        Item.setmNewActivity(1);
        arrayOfUpadates.add(Item);

        ListView listUpadtes = (ListView) findViewById(R.id.UpdatePage_listview);
        UpdatesAdapter adapter = new UpdatesAdapter(this, arrayOfUpadates);

        listUpadtes.setAdapter(adapter);
    }
}
