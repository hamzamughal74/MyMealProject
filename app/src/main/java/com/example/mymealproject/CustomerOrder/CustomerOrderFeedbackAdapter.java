package com.example.mymealproject.CustomerOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Order;
import com.stepstone.apprating.AppRatingDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerOrderFeedbackAdapter extends RecyclerView.Adapter<CustomerOrderFeedbackAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> orderDetailList;

    public CustomerOrderFeedbackAdapter(Context context, ArrayList<Order> orderDetailList) {
        this.context = context;
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_order_details,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerOrderFeedbackAdapter.ViewHolder viewHolder, int i) {
        final Order order = orderDetailList.get(i);
        viewHolder.productName.setText(order.getProductName());
        viewHolder.productQuantity.setText(order.getQuantity());
        viewHolder.productPrice.setText(order.getPrice());
        viewHolder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AppRatingDialog.Builder()
                        .setPositiveButtonText("Submit")
                        .setNegativeButtonText("Cancel")
                        .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
                        .setDefaultRating(0)
                        .setTitle("Rate this Food")
                        .setDescription("Please select some stars and give your feedback")
                        .setTitleTextColor(R.color.theme_orange)
                        .setDescriptionTextColor(R.color.theme_orange);
//                        .create()
//                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productName,productQuantity,productPrice;
        FloatingActionButton fab;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productPrice= itemView.findViewById(R.id.productPrice);
            fab = itemView.findViewById(R.id.btnRating);


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
