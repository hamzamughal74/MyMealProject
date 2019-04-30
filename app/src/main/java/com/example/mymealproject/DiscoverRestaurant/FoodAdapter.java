package com.example.mymealproject.DiscoverRestaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelFood>mList;
    FoodAdapter(Context context, ArrayList<ModelFood>list){
        mContext = context;
        mList = list;
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

        ModelFood foodItem = mList.get(i);

        ImageView mImage;
        TextView mName,mPlace,mPrice;

        mImage = viewHolder.item_image;
        mName = viewHolder.item_name;
        mPlace = viewHolder.item_place;
        mPrice = viewHolder.item_price;

        mImage.setImageResource(mList.get(i).getImage());

        mName.setText(foodItem.getName());
        mPlace.setText(foodItem.getPlace());
        mPrice.setText(foodItem.getPrice());

    }




    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name,item_place,item_price;
        public ViewHolder( View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.media_image);
            item_name = itemView.findViewById(R.id.primary_text);
            item_place = itemView.findViewById(R.id.sub_text);
            item_price = itemView.findViewById(R.id.action_button_1);

        }
    }
}
