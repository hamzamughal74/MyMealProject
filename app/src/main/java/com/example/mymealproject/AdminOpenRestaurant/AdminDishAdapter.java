package com.example.mymealproject.AdminOpenRestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminDishAdapter extends RecyclerView.Adapter<AdminDishAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MenuModel> mDishList;
    AdminDishAdapter(Context context, ArrayList<MenuModel> dishList){
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

try {
    MenuModel menuModel = mDishList.get(i);
    viewHolder.item_name.setText(menuModel.getName());
    viewHolder.item_price.setText(menuModel.getPrice());
     Picasso.with(mContext).load(menuModel.getImageUrl()).fit().into(viewHolder.item_image);

}
catch (Exception e)
{
    Log.d("test", "onBindViewHolder: " + e);
}

    }




    @Override
    public int getItemCount() {
        return mDishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name,item_price;
        public ViewHolder( View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.itemImage);
            item_name = itemView.findViewById(R.id.itemName);
           item_price = itemView.findViewById(R.id.itemPrice);


        }
    }

}
