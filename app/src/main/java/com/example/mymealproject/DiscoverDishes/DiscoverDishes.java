package com.example.mymealproject.DiscoverDishes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscoverDishes extends AppCompatActivity {
    private RecyclerView mFoodRecycleView;
    ArrayList<MenuModel> mFoodList;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_restaurant);
        mFoodRecycleView = findViewById(R.id.recyclerViewDR);
        BottomNavigationView bottomNav = findViewById(R.id.main_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        foodListShow();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_discoverRestaurants:

                            Intent intent = new Intent(DiscoverDishes.this , DiscoverDishes.class );
                            finish();
                            startActivity(intent);
                            break;


                    }
                    return false;
                }
            };

    public void foodListShow(){

        mFoodList = new ArrayList<>();


        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mRvLayoutManager = mLayoutManger;
        mFoodRecycleView.setLayoutManager(mRvLayoutManager);
        final FoodAdapter mFoodAdapter = new FoodAdapter(this,mFoodList);
        mFoodRecycleView.setAdapter(mFoodAdapter);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Menu");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    mFoodList.clear();

                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()){
                        MenuModel modelFood = Snapshot.getValue(MenuModel.class);
                        mFoodList.add(modelFood);
                    }
                    mFoodAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(DiscoverDishes.this, "No data found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
