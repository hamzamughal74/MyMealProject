package com.example.mymealproject.AdminOpenRestaurant;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminDishAdapter extends RecyclerView.Adapter<AdminDishAdapter.ViewHolder> {
    DatabaseReference refDish;
    EditText    dishName,dishPrice;
    String newName,newPrice;
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
        View mView = mLayoutInflater.inflate(R.layout.rv_admin_dish,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);



        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

try {
    final MenuModel menuModel = mDishList.get(i);
    viewHolder.item_name.setText(menuModel.getName());
    viewHolder.item_price.setText("Price : "+menuModel.getPrice()+" Rs");
    viewHolder.person.setText("Person : "+menuModel.getPerson());
     Picasso.with(mContext).load(menuModel.getImageUrl()).fit().into(viewHolder.item_image);
    viewHolder.btnEditDish.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            refDish = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").child(menuModel.getID());
        final Dialog dishDialog= new Dialog(mContext);
        dishDialog.setContentView(R.layout.edit_dish_dialog);
        dishDialog.setCancelable(true);
          dishName =  dishDialog.findViewById(R.id.newDishName);
           dishPrice =  dishDialog.findViewById(R.id.newDishPrice);

            Button btnUpdate = dishDialog.findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newName = dishName.getText().toString();
                    newPrice = dishPrice.getText().toString();
                refDish.child("name").setValue(newName);
                refDish.child("price").setValue(newPrice);
                dishDialog.dismiss();
                }
            });
            Button btnCancel = dishDialog.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dishDialog.dismiss();
                }
            });
            dishDialog.show();
        }

    });
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

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView item_image;
        TextView item_name,item_price,person;
        ImageButton btnEditDish;
        private ItemClickListener itemClickListener;
        public ViewHolder( View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.itemImage);
            item_name = itemView.findViewById(R.id.RestName);
           item_price = itemView.findViewById(R.id.itemPrice);
           person = itemView.findViewById(R.id.person);
           btnEditDish = itemView.findViewById(R.id.btnEditDish);


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
