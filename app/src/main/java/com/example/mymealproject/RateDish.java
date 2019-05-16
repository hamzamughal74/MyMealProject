package com.example.mymealproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RateDish extends AppCompatActivity {

    public RecyclerView rvRateDish;
    ArrayList<Order> orderDetailList;

    FirebaseDatabase database;
    DatabaseReference request;


    String orderID;

    TextView productName;
    String foodId;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_dish);
        database = FirebaseDatabase.getInstance();
        request = database.getReference("OrderRequest");
        rvRateDish = findViewById(R.id.rvRateDish);
        Bundle extras = getIntent().getExtras();
        orderID = extras.getString("orderID");
        orderDetailShow();
    }
    private void orderDetailShow() {
        orderDetailList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        rvRateDish.setLayoutManager(rvLayoutManager);
        final RateDishAdapter rateDishAdapter = new RateDishAdapter(this,orderDetailList);


    }

}
