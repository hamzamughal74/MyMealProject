package com.example.mymealproject.AutoDeals;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.CustomerOrder.Orders;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV1Adapter extends RecyclerView.Adapter<RV1Adapter.ViewHolder> {
    private Context context;
    private ArrayList<MenuModel> mDishList;

    public RV1Adapter(Context context, ArrayList<MenuModel> dishList) {
        this.context = context;
        mDishList = dishList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ad_dish_card,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MenuModel  menuModel = mDishList.get(i);
        viewHolder.name.setText(menuModel.getName());
        viewHolder.price.setText(menuModel.getPrice());
        viewHolder.person.setText(menuModel.getPerson());
        Picasso.with(context).load(menuModel.getImageUrl()).fit().into(viewHolder.image);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {

                Intent intent = new Intent(context, Orders.class);
                intent.putExtra("mID",menuModel.getID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,price,person;
        ImageView image;
        ImageButton btnOpenDish;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dishName);
            price = itemView.findViewById(R.id.dishPrice);
            person = itemView.findViewById(R.id.dishPerson);
            image = itemView.findViewById(R.id.itemImage);
            btnOpenDish = itemView.findViewById(R.id.btnOpenDish);
            btnOpenDish.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
