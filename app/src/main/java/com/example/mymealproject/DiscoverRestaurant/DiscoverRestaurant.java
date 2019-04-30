package com.example.mymealproject.DiscoverRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.mymealproject.R;
import com.example.mymealproject.OpenRestaurant.open_restaurant;
import com.example.mymealproject.StaffOpenRestaurant.staff_open_restaurant;

import java.util.ArrayList;

public class DiscoverRestaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    ArrayList<ModelFood> mFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_restaurant);
        mRecyclerView = findViewById(R.id.recyclerViewDR);
        BottomNavigationView bottomNav = findViewById(R.id.main_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        mFoodList = new ArrayList<>();
        mFoodList.add(new ModelFood(R.drawable.food,"Steaks","Savour","Action_1"));
        mFoodList.add(new ModelFood(R.drawable.food,"BBQ","Monal","Action_1"));
        mFoodList.add(new ModelFood(R.drawable.food,"Steaks","Savour","Action_1"));
        mFoodList.add(new ModelFood(R.drawable.food,"BBQ","Monal","Acion_1"));

        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mRecyclerView.setLayoutManager(mRvLayoutManager);
        FoodAdapter mAdapter = new FoodAdapter(this,mFoodList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_discoverRestaurants:

                            Intent intent = new Intent(DiscoverRestaurant.this ,DiscoverRestaurant.class );
                            startActivity(intent);
                            break;
                        case  R.id.nav_discoverDishes:
                            Intent intent1 = new Intent(DiscoverRestaurant.this, open_restaurant.class);
                            startActivity(intent1);
                            break;
                        case R.id.nav_staff:
                            Intent intent2 = new Intent(DiscoverRestaurant.this, staff_open_restaurant.class);
                            startActivity(intent2);
                            break;



                    }
                    return false;
                }
            };

}
