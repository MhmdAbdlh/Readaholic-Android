package com.example.android.readaholic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.home.HomeFragment;
import com.example.android.readaholic.sign_in_up.Start;
import com.example.android.readaholic.sign_in_up.UserInfo;


public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private long mBackPressedTime;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.Main_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.Main_drawerlayout);
        NavigationView navigationView = findViewById(R.id.Main_navView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragmentLayout,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.draw_home_menu);
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.draw_home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragmentLayout,
                        new HomeFragment()).commit();
                break;

            case R.id.draw_followers_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragmentLayout,
                        new MyBooksFragment()).commit();
                break;
            case R.id.draw_settings_menu:

                break;
            case R.id.draw_logout_menu:
                logoutrequest();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            mBackPressedTime = System.currentTimeMillis();

        }
    }

        private void logoutrequest() {
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="https://api.myjson.com/bins/1hdk";
                int statusCode;
                // Request a string response from the provided URL.

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            UserInfo.removeUserInfo();
                            Intent intent = new Intent(getBaseContext(),Start.class);
                            startActivity(intent);
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Main.this, "Connection error", Toast.LENGTH_SHORT).show();
                        }
                    }) ;

            // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }




}
