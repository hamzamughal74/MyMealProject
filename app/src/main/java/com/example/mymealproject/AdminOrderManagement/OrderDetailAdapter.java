package com.example.mymealproject.AdminOrderManagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Order;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Order> orderDetailList;



    public OrderDetailAdapter(Context context, ArrayList<Order> orderDetailList) {
        this.context = context;
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.admin_order_details,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Order order = orderDetailList.get(i);
        viewHolder.productName.setText(order.getProductName());
        viewHolder.productQuantity.setText(order.getQuantity());
        viewHolder.productPrice.setText(order.getPrice());


    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productQuantity,productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                productName = itemView.findViewById(R.id.productName);
                productQuantity = itemView.findViewById(R.id.productQuantity);
                productPrice= itemView.findViewById(R.id.productPrice);

        }
    }
}
