package com.example.mymealproject.AdminOrderManagement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymealproject.CustomerOrder.OrderRequest;
import com.example.mymealproject.CustomerOrder.OrderStatus;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.OrderDetails;
import com.example.mymealproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {
    FirebaseDatabase database;
    DatabaseReference request;
    String status;
    private Context context;
    private ArrayList<OrderRequest> orderList;

    public AdminOrderAdapter(Context context, ArrayList<OrderRequest> orderList) {
        this.context = context;
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View mView  = mLayoutInflater.inflate(R.layout.admin_order_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);


      return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderAdapter.ViewHolder viewHolder, int i) {
        database = FirebaseDatabase.getInstance();
        request = database.getReference("OrderRequest");
        final OrderRequest orderRequest = orderList.get(i);
        viewHolder.orderID.setText(orderRequest.getCustomerId());
        viewHolder.orderStatus.setText("Status : "+covertCodeToStatus(orderRequest.getStatus()));
        viewHolder.orderTableNo.setText("Table No. : "+orderRequest.getTableNo());
//        viewHolder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int i) {
////                Intent intent = new Intent(AdminOrderAdapter.this,OrderDetails.class);
//            }
//        });
viewHolder.btnOrderCompleted.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        request.child(orderRequest.getOrderID()).child("status").setValue("1");
    }
});
viewHolder.btnOrderCancle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        request.child(orderRequest.getOrderID()).removeValue();   }
});



    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderID,orderStatus,orderTableNo;
        Button btnOrderCompleted,btnOrderCancle;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.orderId);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTableNo = itemView.findViewById(R.id.orderTableNo);
            btnOrderCompleted = itemView.findViewById(R.id.btnOrderComplete);
            itemView.setOnClickListener(this);
            btnOrderCancle=itemView.findViewById(R.id.btnOrderCancel);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
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
