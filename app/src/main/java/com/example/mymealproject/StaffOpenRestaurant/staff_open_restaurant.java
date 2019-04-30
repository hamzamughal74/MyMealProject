package com.example.mymealproject.StaffOpenRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.mymealproject.Create;
import com.example.mymealproject.DiscoverRestaurant.DiscoverRestaurant;
import com.example.mymealproject.OpenRestaurant.open_restaurant;
import com.example.mymealproject.R;
import com.example.mymealproject.StaffOpenRestaurant.StaffCatagoryAdapter;
import com.example.mymealproject.StaffOpenRestaurant.StaffDishAdapter;
import com.example.mymealproject.StaffOpenRestaurant.StaffModelCatagory;
import com.example.mymealproject.StaffOpenRestaurant.StaffModelDish;

import java.util.ArrayList;

public class staff_open_restaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView,mRecyclerViewDish;
    ArrayList<StaffModelCatagory> mFoodList;
    ArrayList<StaffModelDish>mDishList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_open_restaurant);

        mRecyclerView = findViewById(R.id.recyclerViewDR2);
        mRecyclerViewDish = findViewById(R.id.recyclerViewDR3);
        BottomNavigationView bottomNav = findViewById(R.id.nav_StaffOpenRestaurant);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //for catagory
        mFoodList = new ArrayList<>();
        mFoodList.add(new StaffModelCatagory(R.drawable.food,"Steaks"));
        mFoodList.add(new StaffModelCatagory(R.drawable.food,"BBQ"));
        mFoodList.add(new StaffModelCatagory(R.drawable.food,"Steaks"));
        mFoodList.add(new StaffModelCatagory(R.drawable.food,"BBQ"));

        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mRecyclerView.setLayoutManager(mRvLayoutManager);
        StaffCatagoryAdapter mAdapter = new StaffCatagoryAdapter(this,mFoodList);
        mRecyclerView.setAdapter(mAdapter);

        //for dishes

        dishContent();

    }
    private void  dishContent(){
        mDishList = new ArrayList<>();
        mDishList.add(new StaffModelDish(R.drawable.food,"Chicken","50Rs"));
        mDishList.add(new StaffModelDish(R.drawable.food,"Chicken","50Rs"));
        mDishList.add(new StaffModelDish(R.drawable.food,"Chicken","50Rs"));
        mDishList.add(new StaffModelDish(R.drawable.food,"Chicken","50Rs"));

        LinearLayoutManager mLayoutMangerDish = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutMangerDish;
        mRecyclerViewDish.setLayoutManager(mRvLayoutManager);
        StaffDishAdapter mAdapter = new StaffDishAdapter(this,mDishList);
        mRecyclerViewDish.setAdapter(mAdapter);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.add:

                            Intent intent = new Intent(staff_open_restaurant.this , Create.class );
                            startActivity(intent);
                            break;



                    }
                    return false;
                }
            };
}
