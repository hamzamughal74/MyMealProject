package com.example.mymealproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.sqlDatabase.Order;

import java.util.List;

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView name,quantity,price;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name  = itemView.findViewById(R.id.productName);
        quantity = itemView.findViewById(R.id.productQuantity);
        price = itemView.findViewById(R.id.productPrice);
    }
}

public class OrderDetailsAdapter extends RecyclerView.Adapter<MyViewHolder>{
    List<Order> myOrders;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_details,viewGroup,false);


        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Order order = myOrders.get(i);
        myViewHolder.name.setText(String.format("Name : %s" ,order.getProductName()));
        myViewHolder.quantity.setText(String.format("Quantity : %s",order.getQuantity()));
        myViewHolder.quantity.setText(String.format("Price : %s",order.getPrice()));
    }

    @Override
    public int getItemCount() {
        return myOrders.size();
    }
}
