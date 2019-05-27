package com.example.mymealproject.CustomerOrder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class CustomerOrderFeedbackAdapter extends RecyclerView.Adapter<CustomerOrderFeedbackAdapter.ViewHolder> {
    FirebaseDatabase database;
    DatabaseReference request;
    DatabaseReference countRef;
    String ratingCount;
    String totalRating;
    String rating;
    String reqID;
    private Context context;
    private ArrayList<Order> orderDetailList;

    public CustomerOrderFeedbackAdapter(Context context, ArrayList<Order> orderDetailList,String reqID) {
        this.context = context;
        this.orderDetailList = orderDetailList;
        this.reqID = reqID;
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
    public void onBindViewHolder(@NonNull final CustomerOrderFeedbackAdapter.ViewHolder viewHolder, final int i) {
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Restaurant");

        final Order order = orderDetailList.get(i);
        viewHolder.productName.setText("Dish : "+order.getProductName());
        viewHolder.productQuantity.setText("Quantity : "+order.getQuantity());
        viewHolder.productPrice.setText("Price : "+order.getPrice());


        viewHolder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RatingBar ratingBar;
                final float[] ratingValue = new float[1];
                final Dialog rankDialog = new Dialog(context);
                rankDialog.setContentView(R.layout.rank_dialog);
                rankDialog.setCancelable(true);
                ratingBar = (RatingBar)rankDialog.findViewById(R.id.bar);

                final TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
//                text.setText("Rating");
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        ratingValue[0] = rating;

//                        text.setText(Arrays.toString(ratingValue));
                    }
                });


                Button updateButton = (Button) rankDialog.findViewById(R.id.btnRateSubmit);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       getCount(order.getProductId(),ratingValue[0]);
                       removeItem(order.getProductId());
                       rankDialog.dismiss();
                        ((Activity)context).finish();
                        Intent intent = new Intent(context,CustomerOrderFeedback.class);
                        intent.putExtra("orderID",reqID);
                        context.startActivity(intent);



                    }
                });
                Button cancelButton = rankDialog.findViewById(R.id.btnRateCanel);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rankDialog.dismiss();
                    }
                });
                rankDialog.show();
            }
        });
//
    }

    private void removeItem(String productId) {
        FirebaseDatabase.getInstance().getReference("OrderRequest").child(reqID).child("orderList")
                .orderByChild("productId").equalTo(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                   String key = snapshot.getKey();
                   FirebaseDatabase.getInstance().getReference("OrderRequest").child(reqID)
                           .child("orderList").child(key).removeValue();
                   Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
               }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
//        Query query = FirebaseDatabase.getInstance().getReference("OrderRequest").child(reqID).child("orderList").orderByChild("productId").equalTo(productId);
//        query.getRef().removeValue();
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("orderList").getValue()!= null) {
//                   String data =  dataSnapshot.getKey();
//                   dataSnapshot.child(data).getRef().removeValue();
//                    Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//                    Toast.makeText(context, "Not working", Toast.LENGTH_SHORT).show();
//                }
////                notifyDataSetChanged();
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }


    private void getCount(String PID, final float RV){
        countRef = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").child(PID);
        countRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ratingCount = dataSnapshot.child("ratingCount").getValue(String.class);
                totalRating = dataSnapshot.child("totalRating").getValue(String.class);

                float newRating = RV;

                totalRating = String.valueOf(Float.parseFloat(totalRating)+ (newRating));
                rating = String.valueOf(Float.parseFloat(totalRating)/ Float.parseFloat(ratingCount));
                ratingCount = String.valueOf(Integer.parseInt(ratingCount) + 1);
                countRef.child("totalRating").setValue(totalRating);
                countRef.child("ratingCount").setValue(ratingCount);
                countRef.child("rating").setValue(rating);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private String[] getCount(String productID) {
//        final String[] ratingCount = new String[1];
//        countRef = database.getReference("Restaurants");
//        Query query = countRef.child("Menu").child("totalRating").orderByChild("id").equalTo(productID);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ratingCount[0] = dataSnapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        return ratingCount;
//    }

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
            String rateingValue;

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
