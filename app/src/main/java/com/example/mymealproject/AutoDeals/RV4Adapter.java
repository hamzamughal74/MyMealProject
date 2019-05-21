package com.example.mymealproject.AutoDeals;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.R;

public class RV4Adapter extends RecyclerView.Adapter<RV4Adapter.ViewHolder> {
    private Context context;

    public RV4Adapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ad_dish_card,viewGroup,false);
        RV4Adapter.ViewHolder viewHolder = new RV4Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView catagoryName,name,price,person;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dishName);
            price = itemView.findViewById(R.id.dishPrice);
            person = itemView.findViewById(R.id.dishPerson);
        }
    }
}
