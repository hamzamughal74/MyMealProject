package com.example.mymealproject.OpenRestaurant;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.CustomerOrder.Orders;
import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MenuModel> mDishList;
    DishAdapter(Context context, ArrayList<MenuModel>dishList){
        mContext = context;
        mDishList = dishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View mView = mLayoutInflater.inflate(R.layout.rv_dish,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MenuModel menuModel = mDishList.get(i);
        viewHolder.item_name.setText(menuModel.getName());
        viewHolder.item_price.setText("Price : "+menuModel.getPrice()+" Rs");
        viewHolder.person.setText("Person : "+menuModel.getPerson());
        Picasso.with(mContext).load(menuModel.getImageUrl()).fit().into(viewHolder.item_image);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int i) {

                Intent intent = new Intent(mContext, Orders.class);
                intent.putExtra("mID",menuModel.getID());
                mContext.startActivity(intent);
            }
        });


    }




    @Override
    public int getItemCount() {
        return mDishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView item_image;
        TextView item_name,item_price,person;
        ImageButton openDish;
        private ItemClickListener itemClickListener;

        public ViewHolder( View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.itemImage);
            item_name = itemView.findViewById(R.id.RestName);
            item_price = itemView.findViewById(R.id.itemPrice);
            person = itemView.findViewById(R.id.person);
            openDish = itemView.findViewById(R.id.btnOpenDish);
            openDish.setOnClickListener(this);

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
