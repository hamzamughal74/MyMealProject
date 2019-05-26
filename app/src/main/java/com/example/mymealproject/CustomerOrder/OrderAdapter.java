package com.example.mymealproject.CustomerOrder;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private Context context;
    private ArrayList<OrderRequest> orderList;

    public OrderAdapter(Context context, ArrayList<OrderRequest> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View mView  = mLayoutInflater.inflate(R.layout.order_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    final OrderRequest orderRequest = orderList.get(i);
//            viewHolder.orderID.setText(orderRequest.getCustomerId());
            viewHolder.orderStatus.setText("Status : "+covertCodeToStatus(orderRequest.getStatus()));
            viewHolder.orderTableNo.setText("Table No. : "+orderRequest.getTableNo());
            if (orderRequest.getStatus().equals("1")){
                viewHolder.btnRate.setVisibility(View.VISIBLE);
                viewHolder.btnRate.setClickable(true);
            }

            viewHolder.btnRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Deleting order on click feedback

                    Intent intent = new Intent(context, CustomerOrderFeedback.class);
                    intent.putExtra("orderID",orderRequest.getOrderID());
                    context.startActivity(intent);
                    FirebaseDatabase.getInstance().getReference("OrderRequest").child(orderRequest.getOrderID())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (!dataSnapshot.child("orderList").exists()){
                                        dataSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                }
            });
//            viewHolder.setItemClickListener(new ItemClickListener() {
//                @Override
//                public void onClick(View view, int i) {
//
//                }
//            });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderID,orderStatus,orderTableNo;
        Button btnRate;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.orderId);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTableNo = itemView.findViewById(R.id.orderTableNo);
            btnRate = itemView.findViewById(R.id.btnRate);
            btnRate.setVisibility(View.GONE);
            btnRate.setClickable(false);
//            itemView.setOnClickListener(this);


        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
    private String covertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Pending";
        else
            return "Completed";

    }
    }

