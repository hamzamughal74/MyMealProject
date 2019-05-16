package com.example.mymealproject.CustomerOrder;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymealproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderStatus extends AppCompatActivity {
    public RecyclerView recyclerView;
   ArrayList<OrderRequest> orderRequestList;


    FirebaseDatabase database;
    DatabaseReference requests;
    FirebaseAuth Auth;
    String currentUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        // Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("OrderRequest");

        recyclerView = findViewById(R.id.listOrders);
//        recyclerView.setHasFixedSize(true);

        Auth = FirebaseAuth.getInstance();
        currentUID = Auth.getCurrentUser().getUid();

        orderListShow();

    }

    private void orderListShow() {

            orderRequestList = new ArrayList<>();
            LinearLayoutManager layoutManager= new LinearLayoutManager(this);
            RecyclerView.LayoutManager rvLayoutManager = layoutManager;
            recyclerView.setLayoutManager(rvLayoutManager);
            final OrderAdapter orderAdapter = new OrderAdapter(this, orderRequestList);
            recyclerView.setAdapter(orderAdapter);

            Query query = requests.orderByChild("customerId").equalTo(currentUID);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        orderRequestList.clear();

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                            OrderRequest orderRequest =snapshot.getValue(OrderRequest.class);
                            orderRequestList.add(orderRequest);
                        }
                        orderAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(OrderStatus.this, "No data found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

//
//    private void loadOrders(String customerID) {
//
//        FirebaseRecyclerOptions<OrderRequest> options =
//                new FirebaseRecyclerOptions.Builder<OrderRequest>()
//                        .setQuery(requests.orderByChild("customerId").equalTo(customerID), OrderRequest.class)
//                        .build();
//        adapter = new FirebaseRecyclerAdapter<OrderRequest, OrderAdapter>(options) {
//
//
//            @NonNull
//            @Override
//            public OrderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_layout, viewGroup, false);
//                return new OrderAdapter(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull OrderAdapter holder, int position, @NonNull OrderRequest model) {
//                holder.orderId.setText(adapter.getRef(position).getKey());
//                holder.orderStatus.setText(covertCodeToStatus(model.getStatus()));
//                holder.orderTableNo.setText(model.getTableNo());
//
//
//            }
//        };
//        recyclerView.setAdapter(adapter);
//        adater.get
//    }




}
