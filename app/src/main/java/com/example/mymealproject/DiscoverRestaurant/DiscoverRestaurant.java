package com.example.mymealproject.DiscoverRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mymealproject.R;
import com.example.mymealproject.OpenRestaurant.open_restaurant;
import com.example.mymealproject.StaffOpenRestaurant.AdminOpenRestaurant;
import com.example.mymealproject.StaffOpenRestaurant.MenuModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscoverRestaurant extends AppCompatActivity {
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

                            Intent intent = new Intent(DiscoverRestaurant.this ,DiscoverRestaurant.class );
                            startActivity(intent);
                            break;
                        case  R.id.nav_discoverDishes:
                            Intent intent1 = new Intent(DiscoverRestaurant.this, open_restaurant.class);
                            startActivity(intent1);
                            break;
                        case R.id.nav_staff:
                            Intent intent2 = new Intent(DiscoverRestaurant.this, AdminOpenRestaurant.class);
                            startActivity(intent2);
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
                    Toast.makeText(DiscoverRestaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
