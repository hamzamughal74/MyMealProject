package com.example.mymealproject;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiscoverRestaurant extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DR_Adapter adapter;
    private List<restaurantModel> mRestaurantModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_restaurant);
        recyclerView = findViewById(R.id.recyclerViewDR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRestaurantModels = new ArrayList<>();
        adapter = new DR_Adapter(this);
        recyclerView.setAdapter(adapter);

    }

}
