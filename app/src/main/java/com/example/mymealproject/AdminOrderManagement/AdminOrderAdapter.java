package com.example.mymealproject.AdminOrderManagement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.CustomerOrder.OrderRequest;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.R;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {
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
        View mView  = mLayoutInflater.inflate(R.layout.order_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);

      return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderAdapter.ViewHolder viewHolder, int i) {
        final OrderRequest orderRequest = orderList.get(i);
        viewHolder.orderID.setText(orderRequest.getCustomerId());
        viewHolder.orderStatus.setText("Status : "+covertCodeToStatus(orderRequest.getStatus()));
        viewHolder.orderTableNo.setText("Table No. : "+orderRequest.getTableNo());
        


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
        TextView orderID,orderStatus,orderTableNo;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.orderId);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTableNo = itemView.findViewById(R.id.orderTableNo);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the Action");
            menu.add(0,0,getAdapterPosition(),"Update");
            menu.add(0,1,getAdapterPosition(),"Delete");
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
