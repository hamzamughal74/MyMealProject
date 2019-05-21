package com.example.mymealproject.CustomerOrder;

import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.R;
import com.example.mymealproject.sqlDatabase.Database;
import com.example.mymealproject.sqlDatabase.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Orders extends AppCompatActivity {
    TextView mDishName,mDishPrice;
    ImageView mDishImage;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    FloatingActionButton mbtnCart;
    ElegantNumberButton mElegantNumberButton;
    RatingBar ratingBar;
    String dishId = "";

    DatabaseReference mDatabaseReference;
    DatabaseReference mRefDish;
    MenuModel currentDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        //Firebase
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Restaurant");
        mRefDish = mDatabaseReference.child("Menu");
        // view
        mElegantNumberButton = findViewById(R.id.btnNumber);
        mbtnCart = findViewById(R.id.btnCart);


        ratingBar = findViewById(R.id.bar);
        mDishName = findViewById(R.id.dishName);
        mDishPrice = findViewById(R.id.dishPrice);
        mDishImage = findViewById(R.id.dishImage);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpendedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppBar);

//        if (getIntent() !=null){
            dishId = getIntent().getStringExtra("mID");
//            if (!dishId.isEmpty()){
                getDishDetails(dishId);
//            }
//        }
        mbtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        dishId,
                        currentDish.getName(),
                        mElegantNumberButton.getNumber(),
                        currentDish.getPrice()

                ));
                Toast.makeText(Orders.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getDishDetails(final String dishId){
        mRefDish.child(dishId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentDish = dataSnapshot.getValue(MenuModel.class);

                Picasso.with(getBaseContext()).load(currentDish.getImageUrl()).into(mDishImage);
                mCollapsingToolbarLayout.setTitle(currentDish.getName());
                mDishPrice.setText(currentDish.getPrice());
                mDishName.setText(currentDish.getName());
                ratingBar.setRating(Float.parseFloat(currentDish.getRating()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
