package com.example.mymealproject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.sqlDatabase.Database;
import com.example.mymealproject.sqlDatabase.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cartName,price;
    public ImageView imageCartCount;
    public Button btnRemove;
    private ItemClickListener itemClickListener;

    public void setCartName(TextView cartName) {
        this.cartName = cartName;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        cartName = itemView.findViewById(R.id.cartItemName);
        price = itemView.findViewById(R.id.cartItemPrice);
        imageCartCount = itemView.findViewById(R.id.cartItemCount);
        btnRemove = itemView.findViewById(R.id.btnRemove);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{
    private List<Order> listData = new ArrayList<>();
    private Context context;
    String restId;
    public CartAdapter(List<Order> listData, Context context,String restId) {
        this.listData = listData;
        this.context = context;
        this.restId = restId;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = (View) inflater.inflate(R.layout.cart_layout,viewGroup ,false);
        return  new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, final int i) {
        TextDrawable drawable  = TextDrawable.builder()
                .buildRound(""+ listData.get(i).getQuantity(), Color.RED);
        cartViewHolder.imageCartCount.setImageDrawable(drawable);

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(i).getPrice()))*(Integer.parseInt(listData.get(i).getQuantity()));
        cartViewHolder.price.setText(fmt.format(price));
        cartViewHolder.cartName.setText(listData.get(i).getProductName());
        cartViewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  Database(context).delItem(listData.get(i).getProductId());
                ((Activity)context).finish();
                Intent intent = new Intent(context,Cart.class);
                intent.putExtra("get",restId);
                context.startActivity(intent);
                Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

//    public void removeItem(int postion){
//        listData.remove(postion);
//        notifyItemRemoved(postion);
//    }
}


