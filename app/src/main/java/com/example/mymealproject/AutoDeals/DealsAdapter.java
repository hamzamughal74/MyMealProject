package com.example.mymealproject.AutoDeals;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mymealproject.CustomerOrder.Orders;
import com.example.mymealproject.Deals;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;

import java.util.ArrayList;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ArrayList<MenuModel>> list;

    public DealsAdapter(Context context, ArrayList<ArrayList<MenuModel>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.deals_card,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DealsAdapter.ViewHolder viewHolder, int i) {
        String dNo = String.valueOf(i);
        viewHolder.dealName.setText("Deal "+dNo);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(context, Deals.class);
                intent.putParcelableArrayListExtra("D",list.get(i));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton openDeal;
        TextView dealName;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
           this.itemClickListener = itemClickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            openDeal = itemView.findViewById(R.id.btnOpenDeal);
            dealName = itemView.findViewById(R.id.dealName);
            openDeal.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

}
