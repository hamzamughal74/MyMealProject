package com.example.mymealproject.DiscoverRestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelFood> mFoodList;
    FoodAdapter(Context context, ArrayList<ModelFood> foodList){
        mContext = context;
        mFoodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View mView = mLayoutInflater.inflate(R.layout.rv_food_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try{
            ModelFood modelFood = mFoodList.get(i);
            viewHolder.item_name.setText(modelFood.getName());
            viewHolder.item_price.setText(modelFood.getPrice());
            viewHolder.item_restName.setText(modelFood.getRestName());
        }
        catch (Exception e){
            Log.d("test", "onBindViewHolder: " + e);
        }



    }




    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name,item_price,item_restName;
        public ViewHolder( View itemView) {
            super(itemView);


            item_name = itemView.findViewById(R.id.itemName);
            item_price = itemView.findViewById(R.id.itemPrice);
            item_restName = itemView.findViewById(R.id.itemRestName);


        }
    }
}
