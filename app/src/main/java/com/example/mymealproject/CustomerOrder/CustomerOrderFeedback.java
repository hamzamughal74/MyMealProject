package com.example.mymealproject.CustomerOrder;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerOrderFeedback extends AppCompatActivity  {

    public RecyclerView orderRecyclerView;
    ArrayList<Order> orderDetailList;

    FirebaseDatabase database;
    DatabaseReference request;


   public String orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_detail);

        database = FirebaseDatabase.getInstance();
        request = database.getReference("OrderRequest");

        orderRecyclerView = findViewById(R.id.orderDetail);
        Bundle extras = getIntent().getExtras();
        orderID = extras.getString("orderID");
        orderDetailShow();
    }
    private void orderDetailShow() {
        orderDetailList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        orderRecyclerView.setLayoutManager(rvLayoutManager);
        final CustomerOrderFeedbackAdapter CODA = new CustomerOrderFeedbackAdapter(this,orderDetailList);
        orderRecyclerView.setAdapter(CODA);

        request.child(orderID).child("orderList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Order order = snapshot.getValue(Order.class);
                    orderDetailList.add(order);
                }
                CODA.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
