package com.example.mymealproject.OpenRestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.R;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelDish> mDishList;
    DishAdapter(Context context, ArrayList<ModelDish>dishList){
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
        viewHolder.item_name.setText(mDishList.get(i).getName());
        viewHolder.item_price.setText(mDishList.get(i).getPrice());
        viewHolder.item_catagory.setText(mDishList.get(i).getCatagory());



//        ModelDish foodItem = mDishList.get(i);
//
//        ImageView mImage;
//        TextView mName,mRs;
//
//        mImage = viewHolder.item_image;
//        mName = viewHolder.item_name;
//
//
//        mImage.setImageResource(mDishList.get(i).getImage());
//
//        mName.setText(foodItem.getName());


    }




    @Override
    public int getItemCount() {
        return mDishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_name, item_catagory,item_price;
        public ViewHolder( View itemView) {
            super(itemView);


            item_name = itemView.findViewById(R.id.itemName);
            item_catagory = itemView.findViewById(R.id.itemRestName);


        }
    }

}
