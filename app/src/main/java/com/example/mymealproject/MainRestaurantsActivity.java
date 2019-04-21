package com.example.mymealproject;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.Switch;

public class MainRestaurantsActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restaurants);
        BottomNavigationView bottomNav = findViewById(R.id.main_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Intent intent = new Intent(MainRestaurantsActivity.this,DiscoverRestaurant.class);
        startActivity(intent);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_discoverRestaurants:
                            Intent intent = new Intent(MainRestaurantsActivity.this ,DiscoverRestaurant.class );

                            startActivity(intent);


                    }
                    return false;
                }
            };

    }

