package com.example.mymealproject.OpenRestaurant;

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

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelDish>mList;
    DishAdapter(Context context, ArrayList<ModelDish>list){
        mContext = context;
        mList = list;
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

        ModelDish foodItem = mList.get(i);

        ImageView mImage;
        TextView mName,mRs;

        mImage = viewHolder.item_image;
        mName = viewHolder.item_name
        ;


        mImage.setImageResource(mList.get(i).getImage());

        mName.setText(foodItem.getName());


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


        }
    }

}
