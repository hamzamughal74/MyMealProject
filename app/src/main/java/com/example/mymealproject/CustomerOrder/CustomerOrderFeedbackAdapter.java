package com.example.mymealproject.CustomerOrder;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mymealproject.DiscoverDishes.ItemClickListener;
import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerOrderFeedbackAdapter extends RecyclerView.Adapter<CustomerOrderFeedbackAdapter.ViewHolder> {
    FirebaseDatabase database;
    DatabaseReference request;
    DatabaseReference countRef;
    String rating;
    String ratingCount;
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
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Restaurant");

        final Order order = orderDetailList.get(i);
        viewHolder.productName.setText(order.getProductName());
        viewHolder.productQuantity.setText(order.getQuantity());
        viewHolder.productPrice.setText(order.getPrice());
        viewHolder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RatingBar ratingBar;
                final float[] ratingValue = new float[1];
               final Dialog rankDialog = new Dialog(context);
                rankDialog.setContentView(R.layout.rank_dialog);
                rankDialog.setCancelable(true);
                ratingBar = (RatingBar) rankDialog.findViewById(R.id.bar);

                final TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
//                text.setText("Rating");
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        ratingValue[0] = rating;

                        text.setText(Arrays.toString(ratingValue));
                    }
                });


                Button updateButton = (Button) rankDialog.findViewById(R.id.btnRateSubmit);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String count = getCount(order.getProductId());
//                        String count="0";
//                        String result = 0;
//
//                        result = String.valueOf(( Integer.parseInt(Arrays.toString(count)))* (Integer.parseInt(Arrays.toString(ratingValue))));
                        request.child("Menu").child(order.getProductId()).child("rating").setValue(Arrays.toString(ratingValue));
//                    }
//                });
                        Button cancelButton = rankDialog.findViewById(R.id.btnRateCanel);
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        rankDialog.show();
                    }
                });
//
            }

            private String getCount(String productID) {
                countRef = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").child(order.getProductId());
                countRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ratingCount = dataSnapshot.child("ratingCount").getValue(String.class);
                        rating = dataSnapshot.child("rating").getValue(String.class);
                        rating = String.valueOf(Integer.parseInt(rating) + Integer.parseInt(ratingCount));
                        countRef.child("rating").setValue(rating);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                return ratingCount;
            }



        });
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName, productQuantity, productPrice;
        FloatingActionButton fab;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productPrice = itemView.findViewById(R.id.productPrice);
            fab = itemView.findViewById(R.id.btnRating);
            String rateingValue;

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}