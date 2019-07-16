package com.example.mymealproject.AutoDeals;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DealsDetailesAdapter extends RecyclerView.Adapter<DealsDetailesAdapter.ViewHolder> {
    private ArrayList<MenuModel> AL;
    private Context context;

    public DealsDetailesAdapter(ArrayList<MenuModel> AL, Context context) {
        this.AL = AL;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.detail_card,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MenuModel model = AL.get(i);
        viewHolder.name.setText(model.getName());
        viewHolder.person.setText(model.getPerson()+"P");
        viewHolder.price.setText(model.getPrice()+"Rs");
        Picasso.with(context).load(model.getImageUrl()).fit().into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return AL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,person,price;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.itemName);
            person = itemView.findViewById(R.id.itemPerson);
            price = itemView.findViewById(R.id.itemPrice);
            image = itemView.findViewById(R.id.itemImage);

        }
    }
}
