package com.example.mymealproject.OpenRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymealproject.Cart;
import com.example.mymealproject.DashBoard1;
import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.example.mymealproject.dataModelRest;
import com.example.mymealproject.sqlDatabase.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class open_restaurant extends AppCompatActivity {
    private RecyclerView mRecyclerView, mRecyclerViewDish;
    ArrayList<ModelCatagory> mFoodList;
    ArrayList<MenuModel> mDishList;
    DatabaseReference mDatabaseReference;
    DishAdapter mAdapter;
    TextView RestName;
    TextView RestAdress;
    String string;
    ImageView RestImage;
Database mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_restaurant);
        Bundle extras = getIntent().getExtras();
        string  = extras.getString("rID");

        mRecyclerViewDish = findViewById(R.id.recyclerViewDish);
        RestName = findViewById(R.id.RestName);
        RestAdress = findViewById(R.id.RestAddress);
        RestImage = findViewById(R.id.itemImage);

        //for dishes

        dishContent();
        restDetails();

        new  Database(getBaseContext()).cleanCart();

    }



    public void btnAll(View view){
        getAll();
    }
    public void btnRice(View view){
        getCatagory("Rice");
    }
    public void btnBBQ(View view){
        getCatagory("BarBeQue");
    }
    public void btnPakistani(View view){
        getCatagory("Pakistani");
    }
    public void btnFastFood(View view){
        getCatagory("FastFood");
    }



    private void getAll() {
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("rID").equalTo(string);
        mDishList = new ArrayList<>();
        mRecyclerViewDish.setLayoutManager(new LinearLayoutManager(this));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDishList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel dishList = dataSnapshot1.getValue(MenuModel.class);

                            mDishList.add(dishList);


                    }
                    mAdapter = new DishAdapter(open_restaurant.this, mDishList);
                    mRecyclerViewDish.setAdapter(mAdapter);

                } else {
                    mDishList.clear();
                    Toast.makeText(open_restaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(open_restaurant.this, "Oops..!Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getCatagory(final String cat) {
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("rID").equalTo(string);
        mDishList = new ArrayList<>();
        mRecyclerViewDish.setLayoutManager(new LinearLayoutManager(this));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDishList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel dishList = dataSnapshot1.getValue(MenuModel.class);
                        if (dishList.getCatagory().equals(cat)){
                            mDishList.add(dishList);
                        }

                    }
                    mAdapter = new DishAdapter(open_restaurant.this, mDishList);
                    mRecyclerViewDish.setAdapter(mAdapter);

                } else {
                    mDishList.clear();
                    Toast.makeText(open_restaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(open_restaurant.this, "Oops..!Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnDashBoard(View view){
        Intent  db = new Intent(open_restaurant.this, DashBoard1.class);
        db.putExtra("rID",string);
        startActivity(db);
    }
    public void btnCart(View view){
        Intent cart = new Intent(open_restaurant.this,Cart.class );
                            cart.putExtra("get",string);
                            startActivity(cart);
    }
    private void dishContent() {

        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").child("Menu").orderByChild("rID").equalTo(string);
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Menu");
        mDishList = new ArrayList<>();
        mRecyclerViewDish.setLayoutManager(new LinearLayoutManager(this));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mDishList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        MenuModel dishList = dataSnapshot1.getValue(MenuModel.class);
                        mDishList.add(dishList);
                    }
                    mAdapter = new DishAdapter(open_restaurant.this, mDishList);
                    mRecyclerViewDish.setAdapter(mAdapter);

                } else {
                    mDishList.clear();
                    Toast.makeText(open_restaurant.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(open_restaurant.this, "Oops..!Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void restDetails(){
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("rID");
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant").orderByKey().equalTo(string);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    dataModelRest dataModelRest = Snapshot.getValue(dataModelRest.class);
                    RestName.setText(dataModelRest.getName());
                    RestAdress.setText(dataModelRest.getAdress());
                    Picasso.with(open_restaurant.this).load(dataModelRest.getImageUrl()).fit().into(RestImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


