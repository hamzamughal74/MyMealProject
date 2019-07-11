package com.example.mymealproject.IntelliDeal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.CustomerOrder.Orders;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class intelliAdapter extends RecyclerView.Adapter<intelliAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MenuModel> list;

    public intelliAdapter(Context context, ArrayList<MenuModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ad_dish_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MenuModel  menuModel = list.get(i);
        viewHolder.name.setText(menuModel.getName());
        viewHolder.price.setText(menuModel.getPrice()+" Rs");
        viewHolder.person.setText(menuModel.getPerson()+"P");
        Picasso.with(context).load(menuModel.getImageUrl()).fit().into(viewHolder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, person;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dishName);
            price = itemView.findViewById(R.id.dishPrice);
            person = itemView.findViewById(R.id.dishPerson);
            image = itemView.findViewById(R.id.itemImage);

        }
    }
}
