package com.example.mymealproject.CustomerOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.R;

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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View mView  = mLayoutInflater.inflate(R.layout.order_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    final OrderRequest orderRequest = orderList.get(i);
            viewHolder.orderID.setText(orderRequest.getCustomerId());
            viewHolder.orderStatus.setText("Status : "+covertCodeToStatus(orderRequest.getStatus()));
            viewHolder.orderTableNo.setText("Table No. : "+orderRequest.getTableNo());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderID,orderStatus,orderTableNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.orderId);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTableNo = itemView.findViewById(R.id.orderTableNo);



        }

    }
    private String covertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Pending";
        else if (status.equals("1"))
            return "In making";
        else return "Ready";
    }
    }

