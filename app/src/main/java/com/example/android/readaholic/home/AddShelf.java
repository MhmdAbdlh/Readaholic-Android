package com.example.android.readaholic.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class AddShelf extends AppCompatActivity {


    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelf1);

        final RadioButton waanttoread = findViewById(R.id.addshelf_wanttoread_radioButton);
        final RadioButton reading = findViewById(R.id.addshelf_reading_radioButton);
        final RadioButton read = findViewById(R.id.addshelf_read_radioButton);
        Button add = findViewById(R.id.addshelf_add_button_Button);
        ImageView cover = findViewById(R.id.addshelf_bookcover_imageView);
        TextView book = findViewById(R.id.addshelf_bookname_textview);
        TextView authorview = findViewById(R.id.textView6);

        Bundle i = getIntent().getExtras();
        final int id = i.getInt("idBook",0);
        String bookname = i.getString("bookName");
        final String urlimgcover = i.getString("bookImg");
        final int usershelf = i.getInt("bookshelf",0);
        final String author = i.getString("author");
        final int[] shelf = {3};

        authorview.setText(author);
        if(usershelf == 2){
            waanttoread.setChecked(true);
        }else if(usershelf == 1){
            reading.setChecked(true);
        }else if(usershelf == 0){
            read.setChecked(true);
        }


        Picasso.get().load(urlimgcover).into(cover);
        book.setText(bookname);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (waanttoread.isChecked()) {
                        shelf[0] = 2;
                    } else if (reading.isChecked()) {
                        shelf[0] = 1;
                    } else if (read.isChecked()) {
                        shelf[0] = 0;
                    }
                if (UserInfo.IsMemic == false) {
                    requestshelf(shelf[0], id);
                    if (flag == true) {
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Try Again", Toast.LENGTH_LONG);
                    }
                }else{
                    if(id == 1){
                        Memic.book1shelf = shelf[0];
                    }else if(id == 2){
                        Memic.book1shelf = shelf[0];
                    }else if(id == 3){
                        Memic.book1shelf = shelf[0];
                    }else if(id == 4){
                        Memic.book1shelf = shelf[0];
                    }
                }
            }
        });
    }
    public void requestshelf(final int shelf, final int bookid){

        StringRequest request = new StringRequest(Request.Method.POST, Urls.ROOT+"/api/shelf/add_book", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                flag = true;
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                flag = false;
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
                params.put("shelf_id",String.valueOf(shelf));
                params.put("book_id",String.valueOf(bookid));
                params.put("token", UserInfo.sToken);
                params.put("type", UserInfo.sTokenType);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(request);
    }

}
