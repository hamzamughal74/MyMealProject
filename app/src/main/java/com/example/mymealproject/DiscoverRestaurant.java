package com.example.mymealproject;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DiscoverRestaurant extends Activity {
    private RecyclerView recyclerView;
    private DR_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_restaurant);
        recyclerView = findViewById(R.id.recyclerViewDR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DR_Adapter(this);
        recyclerView.setAdapter(adapter);    }

}
