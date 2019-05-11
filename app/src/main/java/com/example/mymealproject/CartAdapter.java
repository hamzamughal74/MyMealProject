package com.example.mymealproject;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.sqlDatabase.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cartName,price;
    public ImageView imageCartCount;

    private ItemClickListener itemClickListener;

    public void setCartName(TextView cartName) {
        this.cartName = cartName;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        cartName = itemView.findViewById(R.id.cartItemName);
        price = itemView.findViewById(R.id.cartItemPrice);
        imageCartCount = itemView.findViewById(R.id.cartItemCount);
    }

    @Override
    public void onClick(View v) {

    }
}
class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{
    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = (View) inflater.inflate(R.layout.cart_layout,viewGroup ,false);
        return  new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        TextDrawable drawable  = TextDrawable.builder()
                .buildRound(""+ listData.get(i).getQuantity(), Color.RED);
        cartViewHolder.imageCartCount.setImageDrawable(drawable);

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(i).getPrice()))*(Integer.parseInt(listData.get(i).getQuantity()));
        cartViewHolder.price.setText(fmt.format(price));

        cartViewHolder.cartName.setText(listData.get(i).getProductName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}


